package com.lb02b.chargego.DataObject.AutoDO;

import javax.persistence.*;

@Table(name = "vehicle_type")
public class VehicleType {
    @Id
    private Integer id;

    private String vtype;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "create_by")
    private String createBy;

    private Long price;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return vtype
     */
    public String getVtype() {
        return vtype;
    }

    /**
     * @param vtype
     */
    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Long price) {
        this.price = price;
    }
}