package lazyfarm.server.repositories;

import lazyfarm.server.entities.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository  extends CrudRepository<Device, Long> {

    @Query("SELECT d FROM Device d WHERE d.growBox.id=:growBox")
    List<Device> findAll(@Param("growBox") Long growBox);
}
