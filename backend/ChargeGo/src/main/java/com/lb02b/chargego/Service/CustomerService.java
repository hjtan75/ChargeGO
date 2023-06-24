package com.lb02b.chargego.Service;


import com.lb02b.chargego.DataObject.AutoDO.Customer;

import java.util.List;

public interface CustomerService {

    boolean customerExist(String customerAccount);

    boolean customerExistById(String customerId);

    void createCustomer(Customer customer);

    Customer customerLogin(String customerAccount, String password);

    Customer updateCustomer(Customer customer);

    Customer getCustomerInfo(String customerId);

    //For Manager
    List<Customer> getAllCustomer();



}
