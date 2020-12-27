package com.example.lib;

import com.example.lib.robotcore.*;

public class DeviceMapping{
    public DcMotor get(String tag) { //could overload with servo, sensor, etc
        return (DcMotor) new DcMotorImpl(tag);
    }
}
