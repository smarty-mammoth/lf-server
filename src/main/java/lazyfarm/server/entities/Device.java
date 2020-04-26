package lazyfarm.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
//@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    @JoinColumn(name = "growbox_id", nullable = false)
    private GrowBox growBox;

    public Device(String name, String address, GrowBox growBox) {
        this.name = name;
        this.address = address;
        this.growBox = growBox;
    }
}
