package hu.elte.alkfejl.classroomApplication.api;

import hu.elte.alkfejl.classroomApplication.model.Room;
import hu.elte.alkfejl.classroomApplication.service.ReservationService;
import hu.elte.alkfejl.classroomApplication.service.RoomService;
import hu.elte.alkfejl.classroomApplication.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomApiController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<Iterable<Integer>> list() {
        return ResponseEntity.ok(reservationService.reservedRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> read(@PathVariable int id) {
        return ResponseEntity.ok(roomService.read(id));
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Room.RoomStatus> isReserved(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getRoomStatus(id));
    }

}