package lazyfarm.server.controllers;

import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.AuthResponseData;
import lazyfarm.server.response.CodeError;
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
            if (login.isEmpty()) throw new APIException(CodeError.LOGIN_IS_EMPTY);
            if (password.isEmpty()) throw new APIException(CodeError.PASSWORD_IS_EMPTY);
            if (userService.userExists(login)) throw new APIException(CodeError.LOGIN_EXISTS);
            userService.addUser(new User(login, userService.calculateHash(password)));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
        return response;
    }

    @RequestMapping("sign-in")
    public ResponseData signIn(@RequestParam(value = "login", defaultValue = "") String login,
                               @RequestParam(value = "password", defaultValue = "") String password)
    {
        AuthResponseData response = new AuthResponseData();
        try {
            if (login.isEmpty()) throw new APIException(CodeError.LOGIN_IS_EMPTY);
            if (password.isEmpty()) throw new APIException(CodeError.PASSWORD_IS_EMPTY);
            User user = userService.findUserByLoginAndHash(login, userService.calculateHash(password));
            if (null == user) throw new APIException(CodeError.INCORRECT_PASSWORD);
            user.setToken(userService.calculateToken(user));
            userService.updateUser(user);
            response.setToken(user.getToken());
        }

        catch (APIException e) {
            response.setError(e.getError());
        }
        return response;

    }
}
