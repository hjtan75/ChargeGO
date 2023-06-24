package com.lb02b.chargego.DataObject.AutoDO;

import java.util.Date;
import javax.persistence.*;

public class Topup {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "original_balance")
    private Long originalBalance;

    @Column(name = "current_balance")
    private Long currentBalance;

    private Long amount;

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
     * @return original_balance
     */
    public Long getOriginalBalance() {
        return originalBalance;
    }

    /**
     * @param originalBalance
     */
    public void setOriginalBalance(Long originalBalance) {
        this.originalBalance = originalBalance;
    }

    /**
     * @return current_balance
     */
    public Long getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance
     */
    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}