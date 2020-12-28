package com.example.lib;

public class TeamCode {
    public static void main(String[] args) {
        AutonomousOpMode robot = new AutonomousOpMode();
        RobotController.setLinearOpMode(robot);
        RobotServer server = new RobotServer();
        System.out.println("Starting");
        server.start();
    }
}
