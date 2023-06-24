package com.lb02b.chargego.Service.ServiceImpl;

import com.lb02b.chargego.Dao.AutoMapper.VehicleMapper;
import com.lb02b.chargego.Dao.AutoMapper.VehicleTypeMapper;
import com.lb02b.chargego.DataObject.AutoDO.Customer;
import com.lb02b.chargego.DataObject.AutoDO.Vehicle;
import com.lb02b.chargego.Service.VehicleService;
import com.lb02b.chargego.Utils.VehicleStatus;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static com.lb02b.chargego.Utils.VehicleStatus.VehicleStatusMap;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Resource
    private VehicleTypeMapper vehicleTypeMapper;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleMapper.selectAll();
    }

    @Override
    public List<Vehicle> getVehiclesByLocation(int altitude, int latitude) {
        return null;
    }

    @Override
    public List<Vehicle> getVehiclesByStation(String stationId){
        var vehicle = new Vehicle();
        vehicle.setStationid(stationId);
        return vehicleMapper.select(vehicle);
    }


    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setId(UUID.randomUUID().toString());
        vehicleMapper.insert(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        var example = new Example(Vehicle.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("id", vehicle.getId());
        vehicleMapper.updateByExampleSelective(vehicle, example);
        var newVe = vehicleMapper.selectOneByExample(example);
        return newVe;
    }

    @Override
    public boolean vehicleExistById(String id) {
        var ve = new Vehicle();
        ve.setId(id);
        var res = vehicleMapper.selectOne(ve);
        if (res == null){
            return false;
        }
        return true;
    }

    @Override
    public long getVehiclePrice(Vehicle vehicle) {
        var vt = vehicle.getVehicleType();
        var vehicleType = vehicleTypeMapper.selectByPrimaryKey(vt);
        return vehicleType.getPrice();
    }

    public Enum getVehicleStatus(int vehicleStatus){
        return VehicleStatusMap.get(vehicleStatus);
    }

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleMapper.selectByPrimaryKey(id);
    }

    public Vehicle repairVehicle(Vehicle vehicle){
        vehicle.setStatus(VehicleStatus.Ready.getValue());
        vehicleMapper.updateByPrimaryKeySelective(vehicle);
        return vehicle;
    }

    public Vehicle chargeVehicle(Vehicle vehicle){
        vehicle.setRemainingBattery(100);
        vehicle.setStatus(VehicleStatus.Ready.getValue());
        vehicleMapper.updateByPrimaryKeySelective(vehicle);
        return vehicle;
    }

    public Vehicle moveVehicle(Vehicle vehicle,double newAltitue,double newLatitude){
        vehicle.setLongitude(newAltitue);
        vehicle.setLatitude(newLatitude);
        vehicleMapper.updateByPrimaryKeySelective(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAllBrokenAndLowpower(){
        Example example=new Example(Vehicle.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.orEqualTo("status",VehicleStatus.Broken.getValue());
        criteria.orLessThanOrEqualTo("remainingBattery",10);
        List<Vehicle> vehicles = vehicleMapper.selectByExample(example);
        if(vehicles.size()==0) return null;
        else return vehicles;
    }

    public void repairAllBrokenVehicle(){
        Example example=new Example(Vehicle.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("status",VehicleStatus.Broken.getValue());
        List<Vehicle> vehicles=vehicleMapper.selectByExample(example);
        for(Vehicle vehicle:vehicles){
            vehicle.setStatus(VehicleStatus.Ready.getValue());
            vehicleMapper.updateByPrimaryKeySelective(vehicle);
        }
    }

    public void chargeAllLowVehicle(){
        Example example=new Example(Vehicle.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andLessThanOrEqualTo("remainingBattery",10);
        List<Vehicle> vehicles=vehicleMapper.selectByExample(example);
        for(Vehicle vehicle:vehicles){
            vehicle.setRemainingBattery(100);
            vehicleMapper.updateByPrimaryKeySelective(vehicle);
        }
    }

}
