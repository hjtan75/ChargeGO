package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.OrderMapper;
import com.lb02b.chargego.DataObject.AutoDO.Order;
import com.lb02b.chargego.DataObject.AutoDO.Vehicle;
import com.lb02b.chargego.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    //@Autowired
    //private CustomerOrderMapper customerOrderMapper;

    @Override
    public Order createOrder(String customerId, String vehicleId, String location) {
        var order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(customerId);
        order.setVehicleId(vehicleId);
        order.setStartLocation(location);
        order.setStatus(1);
        order.setStartTime(new Date());
        orderMapper.insert(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        var example = new Example(Order.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("id", orderId);
        var order = orderMapper.selectOneByExample(orderId);
        return order;
    }

    @Override
    public List<Order> getOrdersByCxId(String customerId) {
        var example = new Example(Order.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("userId", customerId);
        var orderList = orderMapper.selectByExample(example);
        return orderList;
    }

    @Override
    public Order getActiveOrderByCxId(String customerId) {
//        var order = customerOrderMapper.getLatestOrderForCustomer(customerId);
//        if (order.getStatus() == 1){
//            return order;
//        }
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        var example = new Example(Vehicle.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("id", order.getId());
        orderMapper.updateByExampleSelective(order, example);
        var finishedOrder = orderMapper.selectOneByExample(example);
        return finishedOrder;
    }
}
