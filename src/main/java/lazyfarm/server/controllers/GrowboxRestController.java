package lazyfarm.server.controllers;

import lazyfarm.server.entities.GrowBox;
import lazyfarm.server.response.GrowboxResponseData;
import lazyfarm.server.response.ResponseData;
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

}
