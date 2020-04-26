package lazyfarm.server;

import lazyfarm.server.entities.Device;
import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.repositories.DeviceRepository;
import lazyfarm.server.repositories.GrowboxRepository;
import lazyfarm.server.repositories.SensorRepository;
import lazyfarm.server.services.GrowboxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class TestGrowboxService {
    @Autowired
    private GrowboxService growboxService;
    @Autowired
    private GrowboxRepository growboxRepo;
    @Autowired
    private DeviceRepository deviceRepo;
//    @Autowired
//    private SensorRepository sensorRepo;

    @Test
    public void testAddGrowbox() throws Exception {
        growboxService.addBox("Box1", "...");
        var growBox = growboxRepo.findByName("Box1");
        assertThat(growBox.isPresent()).isEqualTo(true);
    }

    @Test
    public void testAddDevice() throws Exception {
        var box = new GrowBox("Box2", "...");
        growboxRepo.save(box);
        var device = new Device("dev1", "localhost", box);
        growboxService.addDevice(box.getId(), device);
        var foundDevice = deviceRepo.findAll(box.getId()).stream().findFirst();
        assertThat(foundDevice.isPresent()).isEqualTo(true);
        assertThat(foundDevice.get().getName()).isEqualTo("dev1");
    }

    @Test
    public void testAddDevice_GrowboxNotFound() throws Exception {
        Long unexistingGrowbox = 999L;
        assertThatThrownBy(() -> growboxService.addDevice(unexistingGrowbox, new Device("dev1", "localhost", null)))
                .isExactlyInstanceOf(APIException.class);
    }
}
