package com.lb02b.chargego.Controller;

import com.lb02b.chargego.DataObject.AutoDO.Operator;
import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Operator;
import com.lb02b.chargego.DataObject.AutoDO.Vehicle;
import com.lb02b.chargego.Service.CustomerService;
import com.lb02b.chargego.Service.OperatorService;
import com.lb02b.chargego.Service.VehicleService;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.Utils.MD5Utils;
import com.lb02b.chargego.ViewObject.CustomerVO;
import com.lb02b.chargego.ViewObject.OperatorVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class OperatorController extends BasicController{
    @Resource
    private OperatorService operatorService;
    @Resource
    private VehicleService vehicleService;

    @PostMapping("/operator/signup")
    public CommonReturnType signup(@RequestBody Operator cx) throws Exception {

        if ((StringUtils.isBlank(cx.getPhone()) && StringUtils.isBlank(cx.getEmail())) ||
                StringUtils.isBlank(cx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }
        var identity = StringUtils.isBlank(cx.getPhone()) ? cx.getEmail() : cx.getPhone();
        boolean isExist = operatorService.operatorExist(identity);
        if (!isExist){
            cx.setPassword(MD5Utils.getMD5Str(cx.getPassword()));
            operatorService.createOperator(cx);
        }
        else {
            return CommonReturnType.errorMsg("The Operator already exist.");
        }
        cx.setPassword("");
        var cxVo = setUserRedisSession(cx);
        return CommonReturnType.success(cxVo);
    }

    @PostMapping("/operator/signin")
    public CommonReturnType login(@RequestBody Operator cx) throws Exception {
        if ((StringUtils.isBlank(cx.getPhone()) && StringUtils.isBlank(cx.getEmail())) ||
                StringUtils.isBlank(cx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }
        var identity = StringUtils.isBlank(cx.getPhone()) ? cx.getEmail() : cx.getPhone();
        var currCx = operatorService.operatorLogin(identity,
                MD5Utils.getMD5Str(cx.getPassword()));

        if (currCx != null){
            currCx.setPassword("");
            //userData = userService.getUserId(userData);
            var userVO = setUserRedisSession(currCx);
            return CommonReturnType.success(userVO);
        }
        return CommonReturnType.errorMsg("The Email/Phone or password is not correct");
    }

    public OperatorVO setUserRedisSession(Operator cx){
        var uniqueToken = UUID.randomUUID().toString();
        redisUtil.set(USER_REDIS_SESSION + ":" + cx.getId(), uniqueToken, 1000 * 60 * 30);
        var cxVo = new OperatorVO();
        BeanUtils.copyProperties(cx, cxVo);
        cxVo.setToken(uniqueToken);
        return cxVo;
    }

    @PostMapping("/operator/repairVehicle")
    public CommonReturnType repairVehicle(@RequestBody Vehicle vehicle){
        if(StringUtils.isBlank(vehicle.getId())){
            CommonReturnType.errorMsg("This vehicle ID is empty");
        }
        return CommonReturnType.success(vehicleService.repairVehicle(vehicle));
    }

    @PostMapping("/operator/chargeVehicle")
    public CommonReturnType chargeVehicle(@RequestBody Vehicle vehicle){
        if(StringUtils.isBlank(vehicle.getId())){
            CommonReturnType.errorMsg("This vehicle ID is empty");
        }
        return CommonReturnType.success(vehicleService.chargeVehicle(vehicle));
    }

    @PostMapping("/operator/moveVehicle")
    public  CommonReturnType moveVehicle(@RequestBody Map<String, Object> map){
        System.out.println(map.get("newAltitude"));
        if(StringUtils.isBlank((String)map.get("id"))){
            CommonReturnType.errorMsg("This vehicle ID is empty");
        }
        Vehicle vehicle=new Vehicle();
        vehicle.setId((String)map.get("id"));
        double newAltitude=(double)map.getOrDefault("newAltitude",map.getOrDefault("altitude",0.0));
        double newLatitude=(double)map.getOrDefault("newLatitude",map.getOrDefault("latitude",0.0));
        return CommonReturnType.success(vehicleService.moveVehicle(vehicle,newAltitude,newLatitude));
    }

    @PostMapping("/operator/getAllVehicle")
    public CommonReturnType getAllVehicle(){
        return CommonReturnType.success(vehicleService.getAllVehicles());
    }

    @PostMapping("/operator/getAllBrokenAndLowpower")
    public CommonReturnType getAllBrokenAndLowpower(){
        return CommonReturnType.success(vehicleService.getAllBrokenAndLowpower());
    }

    @PostMapping("/operator/repairAllBrokenVehicle")
    public CommonReturnType repairAllBrokenVehicle(){
        vehicleService.repairAllBrokenVehicle();
        return CommonReturnType.success();
    }

    @PostMapping("/operator/chargeAllLowVehicle")
    public CommonReturnType chargeAllLowVehicle(){
        vehicleService.chargeAllLowVehicle();
        return CommonReturnType.success();
    }
}
