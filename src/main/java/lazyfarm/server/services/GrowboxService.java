package lazyfarm.server.services;

import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.repositories.GrowboxRepository;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.entities.Device;
import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.entities.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class GrowboxService {

	@Autowired
	private GrowboxRepository growBoxRepo;
	
    public List<GrowBox> getAllBoxes() {
        return null;
    }

    public Optional<GrowBox> findById(Long id) {
        return null;
    }

    public void addBox(String name, String description) throws Exception {
		growBoxRepo.save(new GrowBox(name, description));
    }

    public Optional<GrowBox> findByName(String name) throws Exception {
        return growBoxRepo.findByName(name);
    }

    public void addDevice(Long idGrowbox, Device device) throws Exception {
		var growBox = growBoxRepo.findById(idGrowbox).orElseThrow(() -> new APIException(CodeError.GROWBOX_NOT_FOUND));

    }

    public void addSensor(Long idGrowbox, Sensor sensor) {

    }

    public List<Sensor> findSensorsById(Long idGrowbox) { return null; }

    public List<Device> findDevicesById(Long id) {
        return null;
    }

}
