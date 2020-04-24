package lazyfarm.server.controllers;

import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.entities.Sensor;
import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.response.Error;
import lazyfarm.server.response.GrowboxResponseData;
import lazyfarm.server.response.ResponseData;
import lazyfarm.server.response.SensorResponseData;
import lazyfarm.server.services.GrowboxService;
import lazyfarm.server.services.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrowboxRestController {

    @Autowired
    private GrowboxService growboxService;
    @Autowired
    private UserSerivce userSerivce;

    @RequestMapping("/")
    public GrowboxResponseData getAll() {
        var response = new GrowboxResponseData();
        response.setGrowBoxes(growboxService.getAllBoxes());
        return response;
    }

    @RequestMapping("/{id}")
    public ResponseData getAllDevices(@PathVariable("id") Long idGrowBox) {
        var response = new  ResponseData();
        growboxService.findDevicesById(idGrowBox);
        return response;
    }

    @RequestMapping("/{id}/sensors")
    public ResponseData getAllSensorsByIdGrowbox(@PathVariable("id") Long idGrowbox) {
        var response = new SensorResponseData();
        try {
            var box = growboxService.findById(idGrowbox)
                    .orElseThrow(() -> new APIException(CodeError.GROWBOX_NOT_FOUND));
            response.setSensors(growboxService.findSensorsById(idGrowbox));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
        return response;
    }

    @RequestMapping("/{id}/sensors/add")
    public ResponseData addSensorToGrowbox(
            @PathVariable("id") Long idGrowbox,
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "description", defaultValue = "") String description)
    {
        var response = new ResponseData();
        try {
            if (name.isEmpty() || description.isEmpty()) throw new APIException(CodeError.NULL_ARGUMENTS);
            if (null == userSerivce.findUserByToken(token)) throw new APIException(CodeError.NOT_AUTHORIZED);
            growboxService.addSensor(idGrowbox, new Sensor(name, description));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
        catch (Exception e) {
            response.setError(new Error(CodeError.UNKNOWN.getValue(), CodeError.UNKNOWN.toString()));
        }
        return response;
    }

}
