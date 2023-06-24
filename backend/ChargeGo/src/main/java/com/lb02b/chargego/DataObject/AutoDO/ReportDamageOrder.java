package com.lb02b.chargego.DataObject.AutoDO;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_damage_order")
public class ReportDamageOrder {
    @Id
    private String id;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "damage_info")
    private String damageInfo;

    @Column(name = "current_location")
    private String currentLocation;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "end_time")
    private Date endTime;

    private Integer status;

    @Column(name = "staff_id")
    private String staffId;

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
     * @return customer_id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return damage_info
     */
    public String getDamageInfo() {
        return damageInfo;
    }

    /**
     * @param damageInfo
     */
    public void setDamageInfo(String damageInfo) {
        this.damageInfo = damageInfo;
    }

    /**
     * @return current_location
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * @return staff_id
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}