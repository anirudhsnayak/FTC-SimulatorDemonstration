package com.example.lib;


import com.example.lib.robotcore.DcMotor;
import com.example.lib.robotcore.DcMotorImpl;
import com.example.lib.robotcore.DcMotorImplEx;

public class TeamCode {
    public static void main(String[] args) {
        AutonomousOpMode robot = new AutonomousOpMode();
        RobotController.setLinearOpMode(robot);
        RobotServer server = new RobotServer();
        System.out.println("Starting");
        server.start();
    }

}
