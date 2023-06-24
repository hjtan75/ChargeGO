package com.lb02b.chargego.ViewObject;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class CustomerVO {
    @Id
    private String id;

    private String token;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private Long balance;


    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}
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

}
