package com.lb02b.chargego.Service;

import com.lb02b.chargego.DataObject.AutoDO.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByLocation(int altitude, int latitude);

    List<Vehicle> getVehiclesByStation(String stationId);

    Vehicle getVehicleById(String id);

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    boolean vehicleExistById(String id);


    long getVehiclePrice(Vehicle vehicle);

    Vehicle repairVehicle(Vehicle vehicle);

    Vehicle chargeVehicle(Vehicle vehicle);

    Vehicle moveVehicle(Vehicle vehicle,double newAltitue,double newLatitude);

    List<Vehicle> getAllBrokenAndLowpower();

    void repairAllBrokenVehicle();

    void chargeAllLowVehicle();
}
