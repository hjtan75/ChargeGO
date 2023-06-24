package com.lb02b.chargego.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum VehicleStatus {
    Ready(0),
    Renting(1),
    Broken(2),
    Charging(3),
    Repairing(4),
    Moving(5);

    private final int value;

    VehicleStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public static final Map<Integer, VehicleStatus> VehicleStatusMap = Map.ofEntries(
            Map.entry(0,Ready),
            Map.entry(1, Renting),
            Map.entry(2, Broken),
            Map.entry(3, Charging),
            Map.entry(4, Repairing),
            Map.entry(5, Moving)
    );
}
