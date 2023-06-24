package com.lb02b.chargego.ViewObject;

import javax.persistence.Column;
import javax.persistence.Id;

public class VehicleVO {

    private String id;

    private Integer vehicleType;

    private Integer status;

    private Integer remainingBattery;

    private Double longitude;

    private Double latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRemainingBattery() {
        return remainingBattery;
    }

    public void setRemainingBattery(Integer remainingBattery) {
        this.remainingBattery = remainingBattery;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double altitue) {
        this.longitude = altitue;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
