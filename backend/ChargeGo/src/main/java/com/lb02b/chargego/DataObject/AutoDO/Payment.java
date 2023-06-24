package com.lb02b.chargego.DataObject.AutoDO;

import java.util.Date;
import javax.persistence.*;

public class Payment {
    @Id
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "total_price")
    private Long totalPrice;

    private Integer status;

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
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * @return pay_time
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
}