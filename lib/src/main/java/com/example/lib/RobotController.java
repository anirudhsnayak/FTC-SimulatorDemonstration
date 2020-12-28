package com.example.lib;

public class RobotController extends Thread{
    static LinearOpMode linearOpMode;
    public static void setLinearOpMode(LinearOpMode opMode){ //use annotations
        linearOpMode=opMode;
    }
    public void run(){
        try {
            linearOpMode.runOpMode();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
