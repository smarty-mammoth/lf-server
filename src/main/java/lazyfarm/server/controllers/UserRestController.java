package lazyfarm.server.controllers;

import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

@RestController
public class UserRestController {
    private final UserSerivce userSerivce;

    public UserRestController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userSerivce.findUserById(id);
    }
}
