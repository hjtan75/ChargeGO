package com.lb02b.chargego.ViewObject;

import com.lb02b.chargego.Dao.AutoMapper.PaymentMapper;
import com.lb02b.chargego.DataObject.AutoDO.Order;
import com.lb02b.chargego.DataObject.AutoDO.Payment;

public class OrderPaymentVO {

    private Order order;

    private Payment payment;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
