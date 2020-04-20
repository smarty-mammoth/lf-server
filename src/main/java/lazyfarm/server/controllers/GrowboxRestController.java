package lazyfarm.server.controllers;

import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.exeptions.APIException;
import lazyfarm.server.response.CodeError;
import lazyfarm.server.response.GrowboxResponseData;
import lazyfarm.server.response.ResponseData;
import lazyfarm.server.response.SensorResponseData;
import lazyfarm.server.services.GrowboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrowboxRestController {

    @Autowired
    private GrowboxService growboxService;

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
            var box = growboxService.findById(idGrowbox);
            if (null == box) throw new APIException(CodeError.GROWBOX_NOT_FOUND);
            response.setSensors(growboxService.findSensorsById(idGrowbox));
        }
        catch (APIException e) {
            response.setError(e.getError());
        }
        return response;
    }

}
