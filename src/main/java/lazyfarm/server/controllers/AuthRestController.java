package lazyfarm.server.controllers;

import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.AuthResponseData;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.response.Error;
import lazyfarm.server.response.ResponseData;
import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth", method = RequestMethod.POST)
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
		catch (Exception e) {
			response.setError(Error.UNKNOWN);
		}
        return response;
    }

    @RequestMapping(value = "sign-in")
    public ResponseData signIn(@RequestParam(value = "login", defaultValue = "") String login,
                               @RequestParam(value = "password", defaultValue = "") String password)
    {
        AuthResponseData response = new AuthResponseData();
        try {
			response.setToken(userService.signIn(login, password));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
		catch (Exception e) {
			response.setError(Error.UNKNOWN);
		}
        return response;

    }
}
