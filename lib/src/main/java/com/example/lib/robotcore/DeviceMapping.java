package com.example.lib.robotcore;

import com.example.lib.robotcore.*;

import java.util.HashMap;

public class DeviceMapping{
    public static HashMap<String, Double> DSValues = new HashMap<String, Double>();
    public DcMotor get(String tag) { //could overload with servo, sensor, etc
        return (DcMotor) new DcMotorImpl(tag);
    }
}
