package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.CustomerMapper;
import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;


    @Override
    public boolean customerExist(String customerAccount) {
        var cx = new Customer();
        if (customerAccount.contains("@")){
            cx.setEmail(customerAccount);
        }else{
            cx.setPhone(customerAccount);
        }
        var res = customerMapper.selectOne(cx);
        if (res == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean customerExistById(String customerId) {
        var cx = new Customer();
        cx.setId(customerId);
        var res = customerMapper.selectOne(cx);
        if (res == null){
            return false;
        }
        return true;
    }

    @Override
    public void createCustomer(Customer customer) {
        var customerId = UUID.randomUUID().toString();
        customer.setId(customerId);
        customer.setBalance(0L);
        customer.setCreateTime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public Customer customerLogin(String customerAccount, String password) {
        var cx = new Customer();
        if (customerAccount.contains("@")){
            cx.setEmail(customerAccount);
        }else{
            cx.setPhone(customerAccount);
        }
        cx.setPassword(password);
        var res = customerMapper.selectOne(cx);
        return res;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        var example = new Example(Customer.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("id", customer.getId());
        customerMapper.updateByExampleSelective(customer, example);
        var newCx = customerMapper.selectOneByExample(example);
        return newCx;
    }

    @Override
    public Customer getCustomerInfo(String customerId) {
        var example = new Example(Customer.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("id", customerId);
        var res = customerMapper.selectOneByExample(example);
        return res;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerMapper.selectAll();
    }


}
