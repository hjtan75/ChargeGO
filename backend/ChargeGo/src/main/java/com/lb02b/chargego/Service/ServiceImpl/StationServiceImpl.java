package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.StationMapper;
import com.lb02b.chargego.DataObject.AutoDO.Station;
import com.lb02b.chargego.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Resource
    private StationMapper stationMapper;

    @Override
    public List<Station> getAllStations() {
        return stationMapper.selectAll();
    }
}
