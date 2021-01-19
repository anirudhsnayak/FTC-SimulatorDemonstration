package com.example.lib.robotcore;

import com.example.lib.robotcore.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.zip.DeflaterOutputStream;

public class DeviceMapping{ //TODO: Make this work based on device type (you can't overload)
    public static HashMap<String, Double> DSValues = new HashMap<String, Double>();
    public static HashMap<String, Double[]> IMUValues = new HashMap<String, Double[]>();
    public DcMotor get(String tag) { //could overload with servo, sensor, etc
        return (DcMotor) new DcMotorImpl(tag);
    }
}
