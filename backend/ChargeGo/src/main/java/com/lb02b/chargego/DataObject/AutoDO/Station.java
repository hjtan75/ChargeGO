package com.lb02b.chargego.DataObject.AutoDO;

import javax.persistence.*;

public class Station {
    @Id
    private String id;

    private Double longitude;

    private Double latitude;

    private String name;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}