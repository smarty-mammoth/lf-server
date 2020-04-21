package lazyfarm.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import lazyfarm.server.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> 	findByToken(@Param("token") String token);
	// User 		findById(long id);
	Optional<User>		findByLogin(String login);
	
}