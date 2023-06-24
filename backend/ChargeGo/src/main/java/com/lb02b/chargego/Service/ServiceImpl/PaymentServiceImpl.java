package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.CustomerMapper;
import com.lb02b.chargego.Dao.AutoMapper.PaymentMapper;
import com.lb02b.chargego.DataObject.AutoDO.Payment;
import com.lb02b.chargego.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Payment createPayment(String orderId, String userId, long totalPrice) {
        var payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setTotalPrice(totalPrice);
        payment.setCreateTime(new Date());
        payment.setStatus(1);
        paymentMapper.insert(payment);
        return payment;
    }

    @Override
    public boolean processPayment(Payment payment) {
        // Todo
        var customer = customerMapper.selectByPrimaryKey(payment.getUserId());
        var balance = customer.getBalance();
        var price = payment.getTotalPrice();
        if (balance < price){
            return false;
        }
        customer.setBalance(balance-price);
        customerMapper.updateByPrimaryKey(customer);
        payment.setStatus(2);
        payment.setPayTime(new Date());
        paymentMapper.updateByPrimaryKey(payment);
        return true;
    }

    @Override
    public Payment getPaymentId(String paymentId) {
        var payment = new Payment();
        payment.setId(paymentId);
        return paymentMapper.selectByPrimaryKey(payment);
    }
}
