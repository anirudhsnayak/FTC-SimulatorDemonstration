package com.example.lib.robotcore;

public interface DistanceSensor extends HardwareDevice {
    double getDistance(DistanceUnit unit);
    double distanceOutOfRange = DistanceUnit.infinity;
}
