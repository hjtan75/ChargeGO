package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.OperatorMapper;
import com.lb02b.chargego.Dao.AutoMapper.OperatorMapper;
import com.lb02b.chargego.DataObject.AutoDO.Operator;
import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Operator;
import com.lb02b.chargego.Service.OperatorService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    OperatorMapper operatorMapper;
    @Override
    public boolean operatorExist(String operatorAccount) {
        var cx = new Operator();
        if (operatorAccount.contains("@")){
            cx.setEmail(operatorAccount);
        }else{
            cx.setPhone(operatorAccount);
        }
        var res = operatorMapper.selectOne(cx);
        if (res == null){
            return false;
        }
        return true;
    }

    @Override
    public void createOperator(Operator operator) {
        var operatorId = UUID.randomUUID().toString();
        operator.setId(operatorId);
        operator.setCreateTime(new Date());
        operatorMapper.insert(operator);
    }

    @Override
    public Operator operatorLogin(String operatorAccount, String password) {
        Example example=new Example(Operator.class);
        Example.Criteria criteria = example.createCriteria();
        if (operatorAccount.contains("@")){
            criteria.andEqualTo("email",operatorAccount);
        }else{
            criteria.andEqualTo("phone",operatorAccount);
        }
        criteria.andEqualTo("password",password);
        var res = operatorMapper.selectByExample(example);
        if(res.size()==0) return null;
        else return res.get(0);
    }
}
