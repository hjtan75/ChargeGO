package com.lb02b.chargego.Controller;

import com.lb02b.chargego.DataObject.AutoDO.Manager;
import com.lb02b.chargego.Service.ManagerService;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.Utils.MD5Utils;
import com.lb02b.chargego.ViewObject.ManagerVO;
import com.lb02b.chargego.ViewObject.revenuePreviousTenMonthsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class ManagerSignController extends BasicController{

    String[] month = {"January", "February", "March", "April", "May", "June", "july", "August", "September", "October", "November", "December"};

    @Resource
    private ManagerService managerService;

    @PostMapping("/manager/signup")
    public CommonReturnType ManagerSignup(@RequestBody Manager mx) throws Exception {

        System.out.println(mx.getPhone());
        System.out.println(mx.getEmail());

        if ((StringUtils.isBlank(mx.getPhone()) && StringUtils.isBlank(mx.getEmail())) ||
                StringUtils.isBlank(mx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }

        var identity = StringUtils.isBlank(mx.getPhone()) ? mx.getEmail() : mx.getPhone();
        boolean isExist = managerService.ManagerExist(identity);
        if (!isExist){
            mx.setPassword(MD5Utils.getMD5Str(mx.getPassword()));
            managerService.createManager(mx);
        }
        else {
            return CommonReturnType.errorMsg("The manager already exist.");
        }
        mx.setPassword("");
        var mxVo = setUserRedisSession(mx);
        return CommonReturnType.success(mxVo);
    }

    @PostMapping("/manager/signin")
    public CommonReturnType Managerlogin(@RequestBody Manager mx) throws Exception {
        System.out.println(mx.getEmail());
        System.out.println(mx.getPassword());
        if ((StringUtils.isBlank(mx.getPhone()) && StringUtils.isBlank(mx.getEmail())) ||
                StringUtils.isBlank(mx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }
        var identity = StringUtils.isBlank(mx.getPhone()) ? mx.getEmail() : mx.getPhone();
        var currCx = managerService.ManagerLogin(identity,
                MD5Utils.getMD5Str(mx.getPassword()));

        if (currCx != null){
            currCx.setPassword("");
            //userData = userService.getUserId(userData);
            var userVO = setUserRedisSession(currCx);
            return CommonReturnType.success(userVO);
        }
        return CommonReturnType.errorMsg("The Email/Phone or password is not correct");
    }

    @GetMapping("/manager/revenuePreviousTenMonths")
    public Map<String, BigDecimal> revenuePreviousTenMonths() throws Exception{

        List<revenuePreviousTenMonthsVO> Revenue = new ArrayList<revenuePreviousTenMonthsVO>();
        Revenue = managerService.findrevenue();
        Map<String, BigDecimal> RevenueMap = new HashMap<>();
        for (revenuePreviousTenMonthsVO revenue : Revenue){
            String monthstr = month[getMonth(revenue.getPay_time()) - 1];
            BigDecimal price = revenue.getTotal_price();
            if(RevenueMap.containsKey(monthstr)){
                RevenueMap.put(monthstr,RevenueMap.get(monthstr).add(price));
            }else {
                RevenueMap.put(monthstr,price);
            }
        }

        return RevenueMap;
    }

    @GetMapping("/manager/numOfBrokenVehiclesPreviousTenMonths")
    public Map<String, Integer> numOfBrokenVehiclesPreviousTenMonths() throws Exception{

        List<revenuePreviousTenMonthsVO> Revenue = new ArrayList<revenuePreviousTenMonthsVO>();
        Revenue = managerService.findbroken();
        Map<String, Integer> RevenueMap = new HashMap<>();
        for (revenuePreviousTenMonthsVO revenue : Revenue){
            String monthstr = month[getMonth(revenue.getCreate_time()) - 1];
            if(RevenueMap.containsKey(monthstr)){
                RevenueMap.put(monthstr,RevenueMap.get(monthstr)+1);
            }else {
                RevenueMap.put(monthstr,1);
            }
        }

        return RevenueMap;
    }

    @GetMapping("/manager/currentNumAvailableVehicle")
    public Integer currentNumAvailableVehicle() throws Exception{

        int availablevehicle = managerService.findAvailable();

        return availablevehicle;
    }

    @GetMapping("/manager/currentNumUnavailableVehicle")
    public Integer currentNumUnavailableVehicle() throws Exception{

        int unavailablevehicle = managerService.findUnavailable();

        return unavailablevehicle;
    }

    @GetMapping("/manager/operatorFixAndMove")
    public Map<String, Integer> operatorFixAndMove() throws Exception{

        List<revenuePreviousTenMonthsVO> Revenue = new ArrayList<revenuePreviousTenMonthsVO>();
        Revenue = managerService.findoperatormove();
        Map<String, Integer> RevenueMap = new HashMap<>();
        for (revenuePreviousTenMonthsVO revenue : Revenue){
            String operator = revenue.getStaff_id();
            if(RevenueMap.containsKey(operator)){
                RevenueMap.put(operator,RevenueMap.get(operator)+1);
            }else {
                RevenueMap.put(operator,1);
            }
        }

        return RevenueMap;
    }

    private Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        return Integer.valueOf(month) + 1;
    }


    private ManagerVO setUserRedisSession(Manager mx) {
        var uniqueToken = UUID.randomUUID().toString();
        redisUtil.set(USER_REDIS_SESSION + ":" + mx.getId(), uniqueToken, 1000 * 60 * 30);
        var mxVo = new ManagerVO();
        BeanUtils.copyProperties(mx, mxVo);
        mxVo.setToken(uniqueToken);
        return mxVo;

    }

}
