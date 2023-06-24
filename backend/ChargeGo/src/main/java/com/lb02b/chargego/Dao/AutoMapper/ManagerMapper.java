package com.lb02b.chargego.Dao.AutoMapper;

import com.lb02b.chargego.DataObject.AutoDO.Manager;
import com.lb02b.chargego.Utils.MyMapper;
import com.lb02b.chargego.ViewObject.ManagerVO;
import com.lb02b.chargego.ViewObject.revenuePreviousTenMonthsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagerMapper extends MyMapper<Manager> {

    @Select("select pay_time, total_price from payment")
    List<revenuePreviousTenMonthsVO> findrevenue();

    @Select("select create_time from report_damage_order")
    List<revenuePreviousTenMonthsVO> findbroken();

    @Select("select count(id) from vehicle where status = 0")
    Integer findAvailable();

    @Select("select count(id) from vehicle where status != 0")
    Integer findUnavailable();

    @Select("select staff_id from move_order")
    List<revenuePreviousTenMonthsVO> findoperatormove();


}