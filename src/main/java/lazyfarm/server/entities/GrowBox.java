package lazyfarm.server.entities;

import java.util.List;
import javax.persistence.*;


@Entity
public class GrowBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "growBox")
    private List<Device> devices;
    @OneToMany(mappedBy = "growBox")
    private List<Sensor> sensors;

    public GrowBox(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
