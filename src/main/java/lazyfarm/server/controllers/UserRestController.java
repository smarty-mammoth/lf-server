package lazyfarm.server.controllers;

import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("user")
public class UserRestController {
    private final UserSerivce userSerivce;

    public UserRestController(UserSerivce userSerivce) {
        this.userSerivce = userSerivce;
    }

    @RequestMapping("{id}")
    public User findUserById(
		@PathVariable("id") Long id,
		@RequestHeader("api-ver") String api) 
	{
		
        return userSerivce.findUserById(id);
    }
}
