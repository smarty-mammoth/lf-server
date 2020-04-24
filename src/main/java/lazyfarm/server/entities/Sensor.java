package lazyfarm.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;
    @ManyToOne
    @JoinColumn(name = "growbox_id", nullable = false)
    private GrowBox growBox;
    private Long channel;

    public Sensor(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
