package com.fastandcurious.ftcsim.robotcore;

import java.util.HashMap;

public class DeviceMapping{
    public static HashMap<String, Double> DSValues = new HashMap<>();
    public static HashMap<String, Double[]> IMUValues = new HashMap<>();
    public static HashMap<String, Long> EncoderValues = new HashMap<>();
    public DcMotor get(String tag) { //could overload with servo, sensor, etc
        return (DcMotor) new DcMotorImpl(tag);
    }
}
