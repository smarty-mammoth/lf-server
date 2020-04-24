package lazyfarm.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    @JoinColumn(name = "growbox_id", nullable = false)
    private GrowBox growBox;
}
