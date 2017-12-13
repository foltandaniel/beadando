package hu.elte.alkfejl.classroomApplication.api;

import hu.elte.alkfejl.classroomApplication.model.User;
import hu.elte.alkfejl.classroomApplication.service.UserService;
import hu.elte.alkfejl.classroomApplication.service.annotations.Role;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserIsExistException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.classroomApplication.model.User.Role.ADMIN;
import static hu.elte.alkfejl.classroomApplication.model.User.Role.USER;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        this.userService.setUser(null);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.register(user));
        } catch (UserIsExistException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
