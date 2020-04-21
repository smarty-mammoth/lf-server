package lazyfarm.server.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import lazyfarm.server.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> 	findByToken(@Param("token") String token);
	User 		findById(long id);
	
}