package hu.elte.alkfejl.classroomApplication.repository;

import hu.elte.alkfejl.classroomApplication.model.Reservation;
import hu.elte.alkfejl.classroomApplication.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}