package lazyfarm.server.services;

import lazyfarm.server.entities.User;
import lazyfarm.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.Null;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserSerivce {
    
	@Autowired
	private UserRepository userRepo;
	
	private final Map<Long, User> users;
	
    public UserSerivce() {
        users = new HashMap<>();
        users.put(1L, new User(1L, "User 1"));
        users.put(2L, new User(2L, "User 2"));
        users.put(3L, new User(3L, "User 3"));
    }

    public User findUserById(Long id) {
        var user = userRepo.findById(id);
		if (user.isPresent()) return user.get();
		return null;
    }
		
    public User findUserByLogin(String login) {
        var res = users.entrySet().stream()
                .filter(u -> u.getValue().getLogin().toLowerCase().equals(login.toLowerCase()))
                .findFirst();
        if (res.isPresent())
            return res.get().getValue();
        return null;
    }
    public boolean userExists(String login) {
        User user = findUserByLogin(login);
        if (null == user) return false;
        return true;
    }
    public User findUserByLoginAndHash(String login, String hash) {
        return users.entrySet().stream()
                .filter(u -> u.getValue().getHash().equals(hash) && u.getValue().getLogin().equals(login))
                .findFirst().get().getValue();
    }

    public User findUserByToken(String token) {
        return null;
    }

    public String calculateHash(String password) {
        return "0xABADBABE" + password;
    }

    public String calculateToken(User user) {
        return "0xABADC0DE" + user.getHash();
    }

    public void addUser(User user) {
        users.put(users.size() + 1L, user);
    }

    public void updateUser(User user) {

    }

}
