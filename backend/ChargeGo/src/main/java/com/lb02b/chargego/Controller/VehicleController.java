package com.lb02b.chargego.Controller;

import com.lb02b.chargego.DataObject.AutoDO.Vehicle;
import com.lb02b.chargego.Service.VehicleService;
import com.lb02b.chargego.Utils.BeanUtil;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.ViewObject.CustomerVO;
import com.lb02b.chargego.ViewObject.VehicleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class VehicleController extends BasicController{

    @Resource
    private VehicleService vehicleService;

    //get vehicle by station
    @GetMapping("/vehicles")
    public CommonReturnType getVehicles(@RequestParam(value = "stationId", defaultValue = "") String stationId){
        if (stationId == ""){
            var vehicleVoList = BeanUtil.copyListProperties(vehicleService.getAllVehicles(), VehicleVO.class);
            return CommonReturnType.success(vehicleVoList);
        }
        // Todo
        var vehicleVoList = BeanUtil.copyListProperties(vehicleService.getVehiclesByStation(stationId), VehicleVO.class);
        return CommonReturnType.success(vehicleVoList);
    }

    @GetMapping("/vehicle/{id}")
    public CommonReturnType getVehicleById(@PathVariable String id){
        var vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null){
            return CommonReturnType.errorMsg("The vehicle doesn't exist. ");
        }
        var vehicleVo = new VehicleVO();
        BeanUtils.copyProperties(vehicle, vehicleVo);
        return CommonReturnType.success(vehicleVo);
    }

    @PostMapping("/vehicle")
    public CommonReturnType addVehicle(@RequestBody Vehicle vehicle){
        var newVe = vehicleService.addVehicle(vehicle);
        var vehicleVo = new VehicleVO();
        BeanUtils.copyProperties(newVe, vehicleVo);
        return CommonReturnType.success(vehicleVo);
    }

    @PutMapping("/vehicle/{id}")
    public CommonReturnType updateVehicle(@RequestBody Vehicle vehicle){
        if (!vehicleService.vehicleExistById(vehicle.getId())){
            return CommonReturnType.errorMsg("The vehicles doesn't exist. ");
        }
        var vehicleFromDB = vehicleService.getVehicleById(vehicle.getId());
        BeanUtils.copyProperties(vehicle, vehicleFromDB, BeanUtil.getNullPropertyNames(vehicle));
        var newVe = vehicleService.updateVehicle(vehicleFromDB);
        var veVo = new VehicleVO();
        BeanUtils.copyProperties(newVe, veVo);

        return CommonReturnType.success(veVo);

    }


}
