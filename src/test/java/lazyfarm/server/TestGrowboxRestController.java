package lazyfarm.server;

import lazyfarm.server.controllers.AuthRestController;
import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.entities.Sensor;
import lazyfarm.server.entities.User;
import lazyfarm.server.response.ResponseData;
import lazyfarm.server.services.GrowboxService;
import lazyfarm.server.services.UserSerivce;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class TestGrowboxRestController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GrowboxService growBoxSerivce;
    @MockBean
    private UserSerivce userSerivce;

    @Test
    public void testGetAllGrowboxes() throws Exception {
        List<GrowBox> boxes = Arrays.asList(new GrowBox("box1"));
        Mockito.when(growBoxSerivce.getAllBoxes()).thenReturn(boxes);
        mockMvc.perform(get("/")).andDo(print()).andExpect(content()
            .json("{'success': true, 'growBoxes':[{'name': 'box1'}]}"));
    }

    @Test
    public void testGetAllSensorsByGrowboxId() throws Exception {
        List<Sensor> sensors = Arrays.asList(new Sensor("Sensor1", ""), new Sensor("Sensor2", "..."));
        Mockito.when(growBoxSerivce.findSensorsById(1L)).thenReturn(sensors);
        Mockito.when(growBoxSerivce.findById(1L)).thenReturn(new GrowBox(""));
        mockMvc.perform(get("/1/sensors")).andExpect(content()
                .json("{'success': true, 'sensors':[{'name':'Sensor1'}, {'name':'Sensor2'}]}"));
    }

    @Test
    public void testAddSensorToGrowbox() throws Exception {
        String mockedToken = "0xABADBABE";
        Mockito.when(growBoxSerivce.findById(1L)).thenReturn(new GrowBox("box1"));
        Mockito.when(userSerivce.findUserByToken(mockedToken)).thenReturn(new User("test", "test"));

        mockMvc.perform(get("/1/sensors/add")
                .param("token", mockedToken)
                .param("name", "sensor1")
                .param("description", "..."))
                .andExpect(content().json("{'success': true}"));
    }


//    @Test
//    public void testSignUpPasswordIsEmpty() throws Exception {
//        Mockito.when(userSerivce.userExists("test")).thenReturn(false);
//        mockMvc.perform(get("/auth/sign-up").param("login", "test"))
//                .andDo(print())
//                .andExpect(content().json("{'success': false}"));
//    }
}
