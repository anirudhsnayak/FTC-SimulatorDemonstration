package com.example.lib.robotcore;

public class Rev2mDistanceSensor implements DistanceSensor {
    String Tag;
    public Rev2mDistanceSensor(String tag){
        Tag = tag;
    }
    public double getDistance(DistanceUnit unit){
        if (DeviceMapping.DSValues.containsKey(Tag)) {
            switch (unit) {
                case CM:
                    return DeviceMapping.DSValues.get(Tag) * 30.48;
                case MM:
                    return DeviceMapping.DSValues.get(Tag) * 304.8;
                case INCH:
                    return DeviceMapping.DSValues.get(Tag) * 12;
                case METER:
                    return DeviceMapping.DSValues.get(Tag) * 0.3048;
            }
        }
        return 0;
    }
    public String getDeviceName() {
        return "REV 2M ToF Distance Sensor";
    }
}
