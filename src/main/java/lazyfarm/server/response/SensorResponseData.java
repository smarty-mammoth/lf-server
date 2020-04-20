package lazyfarm.server.response;

import lazyfarm.server.entities.Sensor;

import java.util.List;

public class SensorResponseData extends ResponseData {
    private List<Sensor> sensors;

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
