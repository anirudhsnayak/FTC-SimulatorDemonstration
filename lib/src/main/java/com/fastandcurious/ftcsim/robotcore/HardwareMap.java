package com.fastandcurious.ftcsim.robotcore;

@SuppressWarnings("unchecked")
public class HardwareMap {
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

