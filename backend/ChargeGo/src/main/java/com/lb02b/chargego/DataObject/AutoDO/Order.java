package com.lb02b.chargego.DataObject.AutoDO;

import java.util.Date;
import javax.persistence.*;

@Table(name = "order_record")
public class Order {
    @Id
    private String id;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "order_duration")
    private Float orderDuration;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "end_location")
    private String endLocation;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private Integer status;

    @Column(name = "report_damage_id")
    private String reportDamageId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return vehicle_id
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * @param vehicleId
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return payment_id
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return order_duration
     */
    public Float getOrderDuration() {
        return orderDuration;
    }

    /**
     * @param orderDuration
     */
    public void setOrderDuration(Float orderDuration) {
        this.orderDuration = orderDuration;
    }

    /**
     * @return total_price
     */
    public Long getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return start_location
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     * @param startLocation
     */
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * @return end_location
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     * @param endLocation
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return report_damage_id
     */
    public String getReportDamageId() {
        return reportDamageId;
    }

    /**
     * @param reportDamageId
     */
    public void setReportDamageId(String reportDamageId) {
        this.reportDamageId = reportDamageId;
    }
}