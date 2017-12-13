package hu.elte.alkfejl.classroomApplication.service;

import hu.elte.alkfejl.classroomApplication.model.Reservation;
import hu.elte.alkfejl.classroomApplication.model.Room;
import hu.elte.alkfejl.classroomApplication.model.Room.RoomStatus;
import hu.elte.alkfejl.classroomApplication.model.User;
import hu.elte.alkfejl.classroomApplication.model.UserReservation;
import hu.elte.alkfejl.classroomApplication.repository.ReservationRepository;
import hu.elte.alkfejl.classroomApplication.repository.RoomRepository;
import hu.elte.alkfejl.classroomApplication.repository.UserRepository;
import hu.elte.alkfejl.classroomApplication.repository.UserReservationRepository;
import hu.elte.alkfejl.classroomApplication.service.exceptions.RoomReservationException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.TimeIntervalException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserIsExistException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static hu.elte.alkfejl.classroomApplication.model.User.Role.USER;

@Service
@SessionScope
@Data
public class ReservationService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserReservationRepository userReservationRepository;

    public Iterable<Reservation> listReservationByRoom(Room room) {
        return reservationRepository.findAllByRoom(room);
    }

    public Iterable<UserReservation> listUserReservationByRoom(Room room) {
        return userReservationRepository.findAllByRoom(room);
    }

    public void delete(int id){
        userReservationRepository.delete(id);
    }

    public Iterable<UserReservation> getUserReservations(User user) {
        if(user.getRole() == User.Role.ADMIN){
            return userReservationRepository.findAll();
        }
        else {
            return userReservationRepository.findAllByUser(user);
        }
    }

    public UserReservation debug(int id){
        UserReservation res = userReservationRepository.findOne(id);
        return  res;
    }

    public UserReservation create(UserReservation userReservation) throws TimeIntervalException, RoomReservationException {
        if(userReservation.getUser() == null){
            throw new RoomReservationException();
        }
        Room room = userReservation.getRoom();
        LocalDateTime start = LocalDateTime.ofInstant(userReservation.getStartTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(userReservation.getEndTime().toInstant(), ZoneId.systemDefault());

        if(!isReserved(room, start, end)){
            return userReservationRepository.save(userReservation);
        } else {
            throw new RoomReservationException();
        }
    }

    public Iterable<Integer> reservedRooms(){
        List<Integer> reservedRoomIds = new ArrayList<>();
        for(Room room : roomRepository.findAll()){
            int id = room.getId();
            if(isReservedNow(id)){
                reservedRoomIds.add(id);
            }
        }
        return reservedRoomIds;
    }

    public RoomStatus getRoomStatus(int id){
        if(isReservedNow(id)){
            return RoomStatus.RESERVED;
        } else {
            return RoomStatus.FREE;
        }
    }

    private boolean isReservedNow(int id) {
        Room room = roomRepository.findOne(id);

        //Keszitunk egy 5 perces intervallumot
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusMinutes(5); //5 perc kesleltetes, hogy ne jelenjen meg szabad teremkent, ha 5 percen belul ora lesz ott

        //Elkapjuk a kivetelt, mert nincs mar ra szuksegunk
        try {
            return isReserved(room, start, end);
        } catch (TimeIntervalException e){
            return true;
        }
    }

    private boolean isReserved(Room room, LocalDateTime start, LocalDateTime end) throws TimeIntervalException {
            LocalDate nowDate = LocalDate.now();

            //Fix termfoglalasok
            for(Reservation reservation : listReservationByRoom(room)){
                if(reservation.getDay().ordinal() == LocalDateTime.now().getDayOfWeek().getValue()) { //Ha a nap megegyezik a mai nappal
                    LocalDateTime reStart = nowDate.atTime(reservation.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime()); //Mai datummal a foglalas kezdesenek idopontja
                    LocalDateTime reEnd = nowDate.atTime(reservation.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime()); //Mai datummal a foglalas vegenek idopontja

                    if( hasTimeCollision(reStart, reEnd, start, end) ){ //Ha idoutkozes tortent
                        return true;
                    }
                }
            }

            //User altal foglalt termek
            for(UserReservation reservation : listUserReservationByRoom(room)){
                LocalDateTime reStart = LocalDateTime.ofInstant(reservation.getStartTime().toInstant(), ZoneId.systemDefault());
                LocalDateTime reEnd = LocalDateTime.ofInstant(reservation.getEndTime().toInstant(), ZoneId.systemDefault());

                if( hasTimeCollision(reStart, reEnd, start, end) ){ //Ha idoutkozes tortent
                    return true;
                }
            }

            return false; //Ha idaig eljutunk, akkor nem foglalt a terem
    }

    private boolean hasTimeCollision(LocalDateTime firstStart, LocalDateTime firstEnd, LocalDateTime secondStart, LocalDateTime secondEnd) throws TimeIntervalException {
        if( firstEnd.isBefore(firstStart) || secondEnd.isBefore(secondStart) ) { //Fel kell tennunk, hogy minden kezdo, illetve vegpont sorrendhelyesen van megadva
            throw new TimeIntervalException();
        }
        return !(firstStart.compareTo(secondStart) <= 0 && firstEnd.compareTo(secondStart) <= 0) || (firstStart.compareTo(secondEnd) >= 0 && firstEnd.compareTo(secondEnd) >= 0);
    }

}
