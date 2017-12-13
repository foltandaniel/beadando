package hu.elte.alkfejl.classroomApplication.service;

import hu.elte.alkfejl.classroomApplication.model.User;
import hu.elte.alkfejl.classroomApplication.repository.UserRepository;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserIsExistException;
import hu.elte.alkfejl.classroomApplication.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import static hu.elte.alkfejl.classroomApplication.model.User.Role.USER;
/*
@Service
public class UserService {
    @Autowired
    private UserRepository userRepoitory;

    public void register(User user) {
        user.setRole(USER);
        userRepoitory.save(user);
    }

    public boolean isValid(User user) {
        return userRepoitory.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
}
*/



@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public User register(User user) throws UserIsExistException {
        if(!isExist(user)) {
            user.setRole(USER);
            this.user = userRepository.save(user);
            return user;
        }
        throw new UserIsExistException();
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }

    public boolean isExist(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
