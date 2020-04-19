package lazyfarm.server.entities;

import java.util.List;

public class GrowBox {
    private Long id;
    private String name;
    private String description;
    private List<Device> devices;
    private List<Sensor> densors;

    public GrowBox(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Sensor> getDensors() {
        return densors;
    }

    public void setDensors(List<Sensor> densors) {
        this.densors = densors;
    }
}
