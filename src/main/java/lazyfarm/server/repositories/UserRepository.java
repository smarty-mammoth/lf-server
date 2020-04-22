package lazyfarm.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import lazyfarm.server.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User>		findByToken(String token);
	Optional<User>		findByLogin(String login);
	Optional<User>		findByLoginAndHash(String login, String hash);
	
}