package com.lb02b.chargego.Controller;

import com.lb02b.chargego.DataObject.AutoDO.Order;
import com.lb02b.chargego.Service.OrderService;
import com.lb02b.chargego.Service.PaymentService;
import com.lb02b.chargego.Service.VehicleService;
import com.lb02b.chargego.Utils.CommonReturnType;
import com.lb02b.chargego.Utils.VehicleStatus;
import com.lb02b.chargego.ViewObject.OrderPaymentVO;
import com.lb02b.chargego.ViewObject.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class OrderController extends BasicController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/orders")
    public CommonReturnType createOrder(@RequestParam(value = "customerId", defaultValue = "")String customerId,
                                        @RequestParam(value = "vehicleId", defaultValue = "") String vehicleId,
                                        @RequestParam(value = "location", defaultValue = "") String location){
        var order = orderService.createOrder(customerId, vehicleId, location);
        var vehicle = vehicleService.getVehicleById(vehicleId);
        vehicle.setStatus(VehicleStatus.Renting.getValue());
        vehicleService.updateVehicle(vehicle);
        var orderVo = new OrderVO();
        BeanUtils.copyProperties(order, orderVo);
        return CommonReturnType.success(orderVo);
    }

    @GetMapping("/orders")
    public CommonReturnType getAllOrder(){
        var orders = orderService.getAllOrders();
        return CommonReturnType.success(orders);
    }

    @PutMapping("/order/{id}")
    public CommonReturnType updateOrder(@PathVariable String id, @RequestBody Order order){
        var orderPaymentVO = new OrderPaymentVO();
        if (order.getStatus() == 2){
            order.setEndTime(new Date());
            var duration = caculateDuration(order.getStartTime(), order.getEndTime());
            var vehicle = vehicleService.getVehicleById(order.getVehicleId());
            var totalPrice = vehicleService.getVehiclePrice(vehicle) * duration;
            var payment = paymentService.createPayment(order.getId(), order.getUserId(), totalPrice);
            order.setPaymentId(payment.getId());
            order.setTotalPrice(totalPrice);
            order.setOrderDuration((float)duration);
            orderPaymentVO.setPayment(payment);

            vehicle.setStatus(VehicleStatus.Ready.getValue());
            vehicleService.updateVehicle(vehicle);
        }else if (order.getStatus() == 3){
            order.setEndTime(new Date());
            var vehicle = vehicleService.getVehicleById(order.getVehicleId());
            vehicle.setStatus(VehicleStatus.Broken.getValue());
            var duration = caculateDuration(order.getStartTime(), order.getEndTime());
            var totalPrice = (long)0;
            order.setTotalPrice(totalPrice);
            order.setOrderDuration((float)duration);
        }
        orderService.updateOrder(order);
        orderPaymentVO.setOrder(order);
        return CommonReturnType.success(orderPaymentVO);
    }

    @GetMapping("/{cusomterId}/orders")
    public CommonReturnType getOrdersForCx(@PathVariable String customerId){
        var orders = orderService.getOrdersByCxId(customerId);
        return CommonReturnType.success(orders);
    }

    @GetMapping("/{cusomterId}/ongoingorder")
    public CommonReturnType getOngoingOrder(@PathVariable String customerId){
        var order = orderService.getActiveOrderByCxId(customerId);
        return CommonReturnType.success(order);
    }

    private long caculateDuration(Date start, Date end){
        var s = start.getTime();
        var e = end.getTime();
        var halfHours = ((e-s)/(1000*60*30)) + 1;
        return halfHours;
    }

    
}
