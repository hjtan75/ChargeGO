package com.lb02b.chargego.Controller;

import com.lb02b.chargego.Service.PaymentService;
import com.lb02b.chargego.Utils.CommonReturnType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class PaymentController extends BasicController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/{paymentId}")
    public CommonReturnType processPayment(@PathVariable String paymentId){
        var payment = paymentService.getPaymentId(paymentId);
        if (payment == null) {
            return CommonReturnType.errorMsg("Payment doesn't exist. ");
        }
        var result = paymentService.processPayment(payment);
        if (result == true){
            return CommonReturnType.success(payment);
        }
        return CommonReturnType.errorMsg("Payment failed.");
    }
}
