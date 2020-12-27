package com.example.lib;
import java.util.*;
import com.example.lib.robotcore.*;


public class AutonomousOpMode extends LinearOpMode{
    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;
    public AutonomousOpMode(){};
    @Override
    public void runOpMode(){
        motorLeftFront = hardwareMap.dcMotor.get("Motor1");
        motorRightFront = hardwareMap.dcMotor.get("Motor2");
        motorLeftBack = hardwareMap.dcMotor.get("Motor3");
        motorRightBack = hardwareMap.dcMotor.get("Motor4");

      while (true){
          StrafeRight();
          sleep(2000);
          StopMotors();
          sleep(2000);
      }
    }
    void SpinClockwise(){
        motorLeftFront.setPower(-200);
        motorLeftBack.setPower(-200);
        motorRightFront.setPower(200);
        motorRightBack.setPower(200);
    }
    void SpinCounterClockwise(){
        motorLeftFront.setPower(200);
        motorLeftBack.setPower(200);
        motorRightFront.setPower(-200);
        motorRightBack.setPower(-200);
    }
    void StrafeLeft(){
        motorLeftFront.setPower(200);
        motorRightFront.setPower(-200);
        motorLeftBack.setPower(200);
        motorRightBack.setPower(-200);
        /*
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor1", "100.0", "200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor2", "100.0", "-200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor3", "100.0", "-200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor4", "100.0", "200.0"}));
        */
    }
    void StrafeRight(){
        motorLeftFront.setPower(-200);
        motorRightFront.setPower(200);
        motorLeftBack.setPower(-200);
        motorRightBack.setPower(200);
        /*
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor1", "100.0", "-200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor2", "100.0", "200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor3", "100.0", "200.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor4", "100.0", "-200.0"}));
        */
    }
    void MoveForward(float p){
        motorLeftFront.setPower(p);
        motorRightFront.setPower(p);
        motorLeftBack.setPower(p);
        motorRightBack.setPower(p);
    }
    void MoveBackward(){
        motorLeftFront.setPower(-200);
        motorRightFront.setPower(-200);
        motorLeftBack.setPower(-200);
        motorRightBack.setPower(-200);
    }
    void StopMotors(){
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor1", "100.0", "0.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor2", "100.0", "0.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor3", "100.0", "0.0"}));
        RobotController.robotEvents.add(new RobotEvent(RobotAction.SET_POWER, new String[]{"Motor4", "100.0", "0.0"}));
    }
}
