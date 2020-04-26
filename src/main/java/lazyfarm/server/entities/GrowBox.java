package lazyfarm.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class GrowBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "growBox", cascade = { CascadeType.ALL })
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
}
