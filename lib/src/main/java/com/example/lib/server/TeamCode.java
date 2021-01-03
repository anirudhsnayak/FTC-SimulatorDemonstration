package com.example.lib.server;

import com.example.lib.server.RobotServer;

public class TeamCode {
    public static void main(String[] args) {
        RobotServer server = new RobotServer();
        System.out.println("Starting");
        server.start();
    }
}
