package com.lb02b.chargego.Controller;
//import com.lb02b.chargego.DataObject.test.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.Service.CustomerService;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.Utils.MD5Utils;
import com.lb02b.chargego.ViewObject.CustomerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class SignupinController extends BasicController{

    @Resource
    private CustomerService customerService;

    @PostMapping("/signup")
    public CommonReturnType signup(@RequestBody Customer cx) throws Exception {
        System.out.println(cx.getPhone());
        System.out.println(cx.getEmail());
        if ((StringUtils.isBlank(cx.getPhone()) && StringUtils.isBlank(cx.getEmail())) ||
                StringUtils.isBlank(cx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }
        var identity = StringUtils.isBlank(cx.getPhone()) ? cx.getEmail() : cx.getPhone();
        boolean isExist = customerService.customerExist(identity);
        if (!isExist){
            cx.setPassword(MD5Utils.getMD5Str(cx.getPassword()));
            customerService.createCustomer(cx);
        }
        else {
            return CommonReturnType.errorMsg("The customer already exist.");
        }
        cx.setPassword("");
        var cxVo = setUserRedisSession(cx);
        return CommonReturnType.success(cxVo);
    }

    @PostMapping("/signin")
    public CommonReturnType login(@RequestBody Customer cx) throws Exception {
        System.out.println(cx.getEmail());
        System.out.println(cx.getPassword());
        if ((StringUtils.isBlank(cx.getPhone()) && StringUtils.isBlank(cx.getEmail())) ||
                StringUtils.isBlank(cx.getPassword())){
            return CommonReturnType.errorMsg("The Email/Phone Number or password cannot be blank.");
        }
        var identity = StringUtils.isBlank(cx.getPhone()) ? cx.getEmail() : cx.getPhone();
        var currCx = customerService.customerLogin(identity,
                MD5Utils.getMD5Str(cx.getPassword()));

        if (currCx != null){
            currCx.setPassword("");
            var userVO = setUserRedisSession(currCx);
            return CommonReturnType.success(userVO);
        }
        return CommonReturnType.errorMsg("The Email/Phone or password is not correct");
    }

    public CustomerVO setUserRedisSession(Customer cx){
        var uniqueToken = UUID.randomUUID().toString();
        redisUtil.set(USER_REDIS_SESSION + ":" + cx.getId(), uniqueToken, 1000 * 60 * 30);
        var cxVo = new CustomerVO();
        BeanUtils.copyProperties(cx, cxVo);
        cxVo.setToken(uniqueToken);
        return cxVo;
    }


}
