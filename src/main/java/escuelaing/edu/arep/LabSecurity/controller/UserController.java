package escuelaing.edu.arep.LabSecurity.controller;

import escuelaing.edu.arep.LabSecurity.entities.User;
import escuelaing.edu.arep.LabSecurity.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User save(@RequestBody User user) {
        try {
            return userService.save(user);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) {
        try {
            return userService.getUser(name);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<User> allUsers()  {
        try {
            return userService.allUsers();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
