package hu.elte.alkfejl.classroomApplication.service;

import hu.elte.alkfejl.classroomApplication.model.Reservation;
import hu.elte.alkfejl.classroomApplication.model.Room;
import hu.elte.alkfejl.classroomApplication.model.UserReservation;
import hu.elte.alkfejl.classroomApplication.repository.ReservationRepository;
import hu.elte.alkfejl.classroomApplication.repository.RoomRepository;
import hu.elte.alkfejl.classroomApplication.repository.UserReservationRepository;
import hu.elte.alkfejl.classroomApplication.service.exceptions.RoomReservationException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.TimeIntervalException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
@Data
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room read(int id){
        return roomRepository.findOne(id);
    }

}
