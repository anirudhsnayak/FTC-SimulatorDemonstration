package com.example.lib.robotcore;

import com.example.lib.DeviceMapping;

public class Rev2mDistanceSensor implements DistanceSensor {
    String Tag;
    public Rev2mDistanceSensor(String tag){
        Tag = tag;
    }
    public double getDistance(DistanceUnit unit){
        return DeviceMapping.DSValues.get(Tag);
    }
    public String getDeviceName() {
        return "REV 2M ToF Distance Sensor";
    }
}
