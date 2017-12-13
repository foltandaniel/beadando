package hu.elte.alkfejl.classroomApplication.api;

import hu.elte.alkfejl.classroomApplication.model.UserReservation;
import hu.elte.alkfejl.classroomApplication.service.ReservationService;
import hu.elte.alkfejl.classroomApplication.service.UserService;
import hu.elte.alkfejl.classroomApplication.service.annotations.Role;
import hu.elte.alkfejl.classroomApplication.service.exceptions.RoomReservationException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.TimeIntervalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.classroomApplication.model.User.Role.ADMIN;
import static hu.elte.alkfejl.classroomApplication.model.User.Role.USER;

@RestController
@RequestMapping("/api/reservation")
public class ReservationApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Role({ADMIN, USER})
    @PostMapping
    public ResponseEntity<UserReservation> create(@RequestBody UserReservation userReservation) {
        userReservation.setUser(userService.getUser());
        try {
            return ResponseEntity.ok(reservationService.create(userReservation));
        } catch (TimeIntervalException e) {
            return ResponseEntity.badRequest().build();
        } catch (RoomReservationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({ADMIN, USER})
    @GetMapping
    public ResponseEntity<Iterable<UserReservation>> list() {
        return ResponseEntity.ok(reservationService.getUserReservations(userService.getUser()));
    }

    @Role({ADMIN, USER})
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Role({USER, ADMIN})
    @GetMapping("/{id}")
    public ResponseEntity<UserReservation> debug(@PathVariable int id){
        return ResponseEntity.ok(reservationService.debug(id));
    }

}