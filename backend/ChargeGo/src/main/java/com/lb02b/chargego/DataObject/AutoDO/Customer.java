package com.lb02b.chargego.DataObject.AutoDO;

import java.util.Date;
import javax.persistence.*;

public class Customer {
    @Id
    private String id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    private Long balance;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return balance
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(Long balance) {
        this.balance = balance;
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
}