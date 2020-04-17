package lazyfarm.server.services;

import lazyfarm.server.entities.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Map;
import java.util.HashMap;

@Service
public class UserSerivce {
    private final Map<Long, User> users;
    public UserSerivce() {
        users = new HashMap<>();
        users.put(1L, new User(1L, "User 1"));
        users.put(2L, new User(2L, "User 2"));
        users.put(3L, new User(3L, "User 3"));
    }

    public User findUserById(Long id) {
        return users.get(id);
    }
    public User findUserByLogin(String login) {
        return users.entrySet().stream()
                .filter(u -> u.getValue().getLogin().toLowerCase() == login.toLowerCase())
                .findFirst().get().getValue();
    }
    public boolean userExists(String login) {
        User user = findUserByLogin(login);
        if (null == user) return false;
        return true;
    }
    public User findUserByHash(String hash) {
        return users.entrySet().stream().filter(u -> u.getValue().getHash() == hash).findFirst().get().getValue();
    }

    public void addUser(User user) {
        users.put(users.size() + 1L, user);
    }

}
