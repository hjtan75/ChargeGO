package com.lb02b.chargego.Controller;


import com.lb02b.chargego.Service.StationService;
import com.lb02b.chargego.Utils.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class StationController extends BasicController{

    @Resource
    private StationService stationService;

    @GetMapping("/stations")
    public CommonReturnType getAllStations(){
        return CommonReturnType.success(stationService.getAllStations());
    }
}
