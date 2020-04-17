package lazyfarm.server.controllers;

import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.response.Error;
import lazyfarm.server.response.ResponseData;
import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthRestController {

    @Autowired UserSerivce userService;

    @RequestMapping("sign-up")
    public ResponseData signUp(@RequestParam(value = "login", defaultValue = "") String login,
                               @RequestParam(value = "password", defaultValue = "") String password)
    {
        ResponseData response = new ResponseData();
        try {
            if (userService.userExists(login)) throw new APIException(CodeError.LOGIN_EXISTS);
            userService.addUser(new User(login, password));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
        return response;
    }

    @RequestMapping("sign-in")
    public ResponseData signIn() {
        return null;

    }
}
