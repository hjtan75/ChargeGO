package com.lb02b.chargego.Service;


import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Manager;
import com.lb02b.chargego.ViewObject.ManagerVO;
import com.lb02b.chargego.ViewObject.revenuePreviousTenMonthsVO;

import java.util.List;

public interface ManagerService {
    boolean ManagerExist(String customerAccount);

    void createManager(Manager manager);

    Manager ManagerLogin(String customerAccount, String password);

    public List<revenuePreviousTenMonthsVO> findrevenue();

    public List<revenuePreviousTenMonthsVO> findbroken();

    public Integer findAvailable();

    public Integer findUnavailable();

    public List<revenuePreviousTenMonthsVO> findoperatormove();
}
