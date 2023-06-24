package com.lb02b.chargego.Service;

import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Operator;

public interface OperatorService {
    boolean operatorExist(String operatorAccount);

    void createOperator(Operator operator);

    Operator operatorLogin(String operatorAccount, String password);
}
