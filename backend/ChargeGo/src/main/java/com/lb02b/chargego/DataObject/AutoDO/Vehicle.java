package com.lb02b.chargego.DataObject.AutoDO;

import javax.persistence.*;

public class Vehicle {
    @Id
    private String id;

    @Column(name = "vehicle_type")
    private Integer vehicleType;

    private Integer status;

    @Column(name = "remaining_battery")
    private Integer remainingBattery;

    private Double longitude;

    private Double latitude;

    @Column(name = "stationId")
    private String stationid;

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
     * @return vehicle_type
     */
    public Integer getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType
     */
    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
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
     * @return remaining_battery
     */
    public Integer getRemainingBattery() {
        return remainingBattery;
    }

    /**
     * @param remainingBattery
     */
    public void setRemainingBattery(Integer remainingBattery) {
        this.remainingBattery = remainingBattery;
    }

    /**
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return stationId
     */
    public String getStationid() {
        return stationid;
    }

    /**
     * @param stationid
     */
    public void setStationid(String stationid) {
        this.stationid = stationid;
    }
}