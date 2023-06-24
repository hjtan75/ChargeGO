package com.lb02b.chargego.Service;

import com.lb02b.chargego.DataObject.AutoDO.Payment;

public interface PaymentService {

    Payment createPayment(String orderId, String userId, long totalPrice);

    boolean processPayment(Payment payment);

    Payment getPaymentId(String paymentId);

}
