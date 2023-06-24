package com.lb02b.chargego.Controller;

import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.Service.CustomerService;
import com.lb02b.chargego.Utils.BeanUtil;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.Utils.VehicleStatus;
import com.lb02b.chargego.ViewObject.CustomerVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.lb02b.chargego.Utils.VehicleStatus.Ready;

@RestController
@Api(value = "Customer related endpoints")
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5500")
public class CustomerController extends BasicController{

    @Resource
    private CustomerService customerService;

    @GetMapping("/{id}")
    public CommonReturnType getCustomerInfo(@PathVariable("id") String id){
        if (StringUtils.isBlank(id)) {
            return CommonReturnType.errorMsg("The ID cannot be blank. ");
        }
        if(!customerService.customerExistById(id)){
            return CommonReturnType.errorMsg("The user is not exist. ");
        }
        var cx = customerService.getCustomerInfo(id);
        var cxVo = new CustomerVO();
        BeanUtils.copyProperties(cx, cxVo);
        return CommonReturnType.success(cxVo);
    }

    @PutMapping("/{id}")
    public CommonReturnType updateCustomerInfo(@PathVariable("id") String id, @RequestBody Customer customer){
        if (!customerService.customerExistById(id)) {
            return CommonReturnType.errorMsg("The user is not exist. ");
        }
        var modelCx = customerService.getCustomerInfo(id);
        BeanUtils.copyProperties(customer, modelCx, BeanUtil.getNullPropertyNames(customer));
        var cx = customerService.updateCustomer(modelCx);
        var cxVo = new CustomerVO();
        BeanUtils.copyProperties(modelCx, cxVo);

        return CommonReturnType.success(cxVo);
    }
}
