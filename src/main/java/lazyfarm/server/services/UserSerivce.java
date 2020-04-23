package lazyfarm.server.services;

import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.entities.User;
import lazyfarm.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.Null;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserSerivce {
    
	@Autowired
	private UserRepository userRepo;
		
    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);
    }
		
    public Optional<User> findUserByLogin(String login) {
		return userRepo.findByLogin(login);
    }
    public boolean userExists(String login) {
        return findUserByLogin(login).isPresent();
    }
    public Optional<User> findUserByLoginAndHash(String login, String hash) {
		return userRepo.findByLoginAndHash(login, hash);
    }

    public Optional<User> findUserByToken(String token) {
		return userRepo.findByToken(token);
    }
	
	public String signIn(String login, String password) throws Exception {
		if (login.isEmpty()) throw new APIException(CodeError.LOGIN_IS_EMPTY);
		if (password.isEmpty()) throw new APIException(CodeError.PASSWORD_IS_EMPTY);
		var user = findUserByLoginAndHash(login, calculateHash(password));
		return user.map(u -> {  
			u.setToken(calculateToken(u));
			updateUser(u);
			return u.getToken();
		})
		.orElseThrow(() -> new APIException(CodeError.INCORRECT_PASSWORD));
	}

    public String calculateHash(String password) {
        return "0xABADBABE" + password;
    }

    public String calculateToken(User user) {
        return "0xABADC0DE" + user.getHash();
    }

    public void addUser(User user) {
		userRepo.save(user);
    }
	
	public void updateUser(User user) {
		userRepo.save(user);
	}
}
