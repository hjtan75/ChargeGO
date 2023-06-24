package com.lb02b.chargego.ViewObject;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class OrderVO {
    private String id;

    private String vehicleId;

    private String userId;

    private String paymentId;

    private Float orderDuration;

    private Long totalPrice;

    private String startLocation;

    private String endLocation;

    private Date startTime;

    private String endTime;

    //1 active 2 fini
    private Integer status;

    private String reportDamageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Float getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(Float orderDuration) {
        this.orderDuration = orderDuration;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReportDamageId() {
        return reportDamageId;
    }

    public void setReportDamageId(String reportDamageId) {
        this.reportDamageId = reportDamageId;
    }

}
