package com.example.lib.robotcore;

import com.example.lib.robotcore.DcMotor;
import com.example.lib.robotcore.DcMotorImpl;
import com.example.lib.robotcore.DeviceMapping;
import com.example.lib.robotcore.DistanceSensor;
import com.example.lib.robotcore.Rev2mDistanceSensor;

@SuppressWarnings("unchecked")
public class HardwareMap {
    public DeviceMapping dcMotor = new DeviceMapping(); //could overload with servo, sensor, etc
    public <T> T get(Class<? extends T> classType, String tag){
        if (classType.equals(DcMotor.class)) {
            return (T) new DcMotorImpl(tag);
        }
        if (classType.equals(DcMotorEx.class)) {
            return (T) new DcMotorImplEx(tag);
        }
        if (classType.equals(DistanceSensor.class)) {
            return (T) new Rev2mDistanceSensor(tag);
        }
        if (classType.equals(BNO055IMU.class)){
            return (T) new BNO055IMUImpl(tag);
        }
        return (null);
    }
}

