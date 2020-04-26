package lazyfarm.server.repositories;

import lazyfarm.server.entities.GrowBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrowboxRepository  extends CrudRepository<GrowBox, Long>{
    Optional<GrowBox> findByName(String name);
    List<GrowBox> findAll();
}
