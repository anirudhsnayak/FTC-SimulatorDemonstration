
package com.example.lib;
import com.example.lib.robotcore.*;

public class AutonomousOpMode extends LinearOpMode{
    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;
    DistanceSensor ds1;
    DistanceSensor ds2;
    @Override
    public void runOpMode(){
        motorLeftFront = hardwareMap.dcMotor.get("Motor1");
        motorRightFront = hardwareMap.dcMotor.get("Motor2");
        motorLeftBack = hardwareMap.dcMotor.get("Motor3");
        motorRightBack = hardwareMap.dcMotor.get("Motor4");
        //ds1 = hardwareMap.get(DistanceSensor.class, "DS1");
        //ds2 = hardwareMap.get(DistanceSensor.class, "DS2");
        sleep(2000);
        StrafeRight();
        sleep(20);
        //TODO: Implement function at the end of runOpMode to send a event package closer to the simulator
    }
    void SpinClockwise(){
        motorLeftFront.setPower(-200);
        motorLeftBack.setPower(-200);
        motorRightFront.setPower(-200);
        motorRightBack.setPower(-200);
    }
    void SpinCounterClockwise(){
        motorLeftFront.setPower(200);
        motorLeftBack.setPower(200);
        motorRightFront.setPower(200);
        motorRightBack.setPower(200);
    }
    void StrafeLeft(){
        motorLeftFront.setPower(200);
        motorRightFront.setPower(-200);
        motorLeftBack.setPower(200);
        motorRightBack.setPower(-200);
    }
    void StrafeRight(){
        motorLeftFront.setPower(-300);
        motorRightFront.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(300);
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
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
    }
    public void makeParallel() {
        while (opModeIsActive()){
            double dis1 = ds1.getDistance(DistanceUnit.CM);
            double dis2 = ds2.getDistance(DistanceUnit.CM);
            System.out.println(ds1.getDistance(DistanceUnit.CM));
            System.out.println(ds2.getDistance(DistanceUnit.CM));
            if (Math.abs(dis1-dis2)<1){
                StopMotors();
                break;
            }
            if (dis1>dis2){
                SpinClockwise();
            } else {
                SpinCounterClockwise();
            }
        }
    }
}

