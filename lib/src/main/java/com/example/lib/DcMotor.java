/*
package com.example.lib;

public class DcMotor {
    String Tag;
    public DcMotor(String tag){
        Tag=tag;
    }
    public void SetPower(double power){
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{Tag, "100000.0", String.valueOf(power)}));
        //need to make some implementation that takes in the power as a value [0, 1], based on the value entered in the robot builder
        //but for now the spin force is virtually infinite and the speed is the power
    }
}
*/