package lazyfarm.server.services;

import lazyfarm.server.entities.Device;
import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.entities.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowboxService {
    public List<GrowBox> getAllBoxes() {
        return null;
    }

    public GrowBox findById(Long id) {
        return null;
    }

    public void addBox(String name) {

    }

    public GrowBox findByName(String name) {
        return new GrowBox(name);
    }

    public void addDevice(String name, String address) {

    }

    public List<Sensor> findSensorsById(Long idGrowbox) { return null; }

    public List<Device> findDevicesById(Long id) {
        return null;
    }

}
