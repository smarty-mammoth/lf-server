package lazyfarm.server;

import lazyfarm.server.entities.User;
import lazyfarm.server.services.UserSerivce;
import lazyfarm.server.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
public class TestUserService {

	@Autowired
	private UserSerivce userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testFindUserById() {
		var user = new User("user1", "0xABADC0DE");
		userRepo.save(user);
		var foundUser = userService.findUserById(user.getId());
		foundUser.ifPresentOrElse(
			u -> assertThat(u.getHash()).isEqualTo(user.getHash()),
			() -> assertThat("Found User canot be null.")
		);
	}
	
	@Test
	public void testFindUserByLogin() {
		var user = new User("user2", "0xABADBABE");
		userRepo.save(user);
		var foundUser = userService.findUserByLogin("user2");
		foundUser.ifPresentOrElse(
			u -> assertThat(u.getHash()).isEqualTo(user.getHash()),
			() -> assertThat("Found user cannot be null.")
		);
	}
	
	@Test
	public void testFindUserByToken() {
		var user = new User("user2", "hash");
		user.setToken("generated-token");
		userRepo.save(user);
		var foundUser = userService.findUserByToken(user.getToken());
		foundUser.ifPresentOrElse(
			u -> assertThat(u.getLogin()).isEqualTo(user.getLogin()),
			() -> assertThat("Found user cannot be null.")
		);
	}
	
	@Test
	public void testFindUserByLoginAndHash() {
		var user = new User("user3", "hash");
		user.setToken("generated-token-for-user-3");
		userRepo.save(user);
		var foundUser = userService.findUserByLoginAndHash(user.getLogin(), user.getHash());
		foundUser.ifPresentOrElse(
			u -> assertThat(u.getToken()).isEqualTo(user.getToken()),
			() -> assertThat("Found user cannot be null.")
		);
	}
	
	@Test
	public void testAddUser() {
		var user = new User("user4", "hash-for-user4");
		userService.addUser(user);
		var foundUser = userRepo.findByLogin(user.getLogin());
		foundUser.ifPresentOrElse(
			u -> assertThat(u.getHash()).isEqualTo(user.getHash()),
			() -> assertThat("Found user cannot be null.")
		);
	}
	
	@Test
	public void FirstTest() {
		var user = new User("user1", "123");
		userRepo.save(user);
		var newUser = userRepo.findById(user.getId());
		assertThat(newUser).isNotNull();
		assertThat(newUser.isPresent()).isEqualTo(true);
	}
	
	
	
}