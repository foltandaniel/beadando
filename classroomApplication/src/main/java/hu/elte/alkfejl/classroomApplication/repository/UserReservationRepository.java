package hu.elte.alkfejl.classroomApplication.repository;

import hu.elte.alkfejl.classroomApplication.model.Room;
import hu.elte.alkfejl.classroomApplication.model.User;
import hu.elte.alkfejl.classroomApplication.model.UserReservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReservationRepository extends CrudRepository<UserReservation, Integer> {
    List<UserReservation> findAllByRoom(Room room);

    List<UserReservation> findAllByUser(User user);
}
