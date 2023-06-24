package com.lb02b.chargego.Service;

import com.lb02b.chargego.DataObject.AutoDO.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(String customerId, String vehicleId, String location);

    List<Order> getAllOrders();

    Order getOrderById(String orderId);

    List<Order> getOrdersByCxId(String customerId);

    Order getActiveOrderByCxId(String customerId);

    Order updateOrder(Order order);


}
