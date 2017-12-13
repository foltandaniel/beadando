package hu.elte.alkfejl.classroomApplication.repository;

import hu.elte.alkfejl.classroomApplication.model.Reservation;
import hu.elte.alkfejl.classroomApplication.model.Room;
import hu.elte.alkfejl.classroomApplication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    List<Reservation> findAllByRoom(Room room);
}