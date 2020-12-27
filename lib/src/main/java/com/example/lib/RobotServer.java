package com.example.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RobotServer extends Thread {

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1.", 5000));
            while (true) {
                System.out.println("Awaiting Connection");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected");
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                String outputLine;
                RobotController robotController = new RobotController();
                robotController.start();
                out.println("RESET");
                connection:
                while (true) {
                    inputLine = in.readLine();
                    if (inputLine != null) {
                        //System.out.println("Client: " + inputLine);
                        if (inputLine.equals("RESET")) {
                            System.out.println("Disconnected");
                            robotController.interrupt();
                            break connection;
                        }
                        outputLine = CreateCommand(inputLine);
                        if (outputLine!= ""){
                            out.println(outputLine);
                        }
                    }
                    Thread.sleep(0);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    String CreateCommand(String input) {
        RobotController.WaitForEventAccess();
        RobotController.OpenEventList();
        if (RobotController.robotEvents.isEmpty()) {
            RobotController.CloseEventList();
            return "";
        }
        StringBuilder cmd = new StringBuilder();
        for (RobotEvent event : RobotController.robotEvents) {
            cmd.append(event.toString());
            cmd.append("/n");
            System.out.print(RobotController.eventsInUse);
            System.out.println(" "+RobotController.robotEvents.size());
        }
        cmd.setLength(cmd.length() - 2);
        RobotController.robotEvents.clear();
        System.out.println(cmd);
        RobotController.CloseEventList();
        return (cmd.toString());
    }
}
