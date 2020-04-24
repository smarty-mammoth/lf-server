package lazyfarm.server.entities;

import javax.persistence.*;

@Entity
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
