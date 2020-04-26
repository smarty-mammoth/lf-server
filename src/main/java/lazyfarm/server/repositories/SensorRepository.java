package lazyfarm.server.repositories;

import lazyfarm.server.entities.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {

//    List<Sensor> findAllByIdGrowbox(Long growbox_id);
}
