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
}
