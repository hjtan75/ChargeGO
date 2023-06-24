package com.lb02b.chargego.Service.ServiceImpl;


import com.lb02b.chargego.Dao.AutoMapper.ManagerMapper;
import com.lb02b.chargego.DataObject.AutoDO.Manager;
import com.lb02b.chargego.Service.ManagerService;
import com.lb02b.chargego.ViewObject.revenuePreviousTenMonthsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ManagerServicelmpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;


    @Override
    public boolean ManagerExist(String managerAccount) {
        var mx = new Manager();
        if (managerAccount.contains("@")){
            mx.setEmail(managerAccount);
        }else{
            mx.setPhone(managerAccount);
        }
        var res = managerMapper.selectOne(mx);
        if (res == null){
            return false;
        }
        return true;
    }

    @Override
    public void createManager(Manager manager) {
        var managerId = UUID.randomUUID().toString();
        manager.setId(managerId);
        manager.setCreateTime(new Date());
        managerMapper.insert(manager);
    }

    @Override
    public Manager ManagerLogin(String customerAccount, String password) {
        var mx = new Manager();
        if (customerAccount.contains("@")){
            mx.setEmail(customerAccount);
        }else{
            mx.setPhone(customerAccount);
        }
        mx.setPassword(password);
        var res = managerMapper.selectOne(mx);
        return res;
    }

    @Override
    public List<revenuePreviousTenMonthsVO> findrevenue(){
        return managerMapper.findrevenue();
    }

    @Override
    public List<revenuePreviousTenMonthsVO> findbroken(){
        return managerMapper.findbroken();
    }

    @Override
    public Integer findAvailable(){
        return managerMapper.findAvailable();
    }

    @Override
    public Integer findUnavailable(){
        return managerMapper.findUnavailable();
    }

    @Override
    public List<revenuePreviousTenMonthsVO> findoperatormove(){
        return managerMapper.findoperatormove();
    }


}
