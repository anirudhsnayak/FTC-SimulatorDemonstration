package com.example.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;

public class RobotServer extends Thread {
    static PrintWriter out;

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1.", 5000));
            while (true) {
                System.out.println("Awaiting Connection");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected");
                out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                RobotController robotController = new RobotController();
                robotController.start();
                out.println("RESET");
                connection:
                while (true) {
                    inputLine = in.readLine();
                    if (inputLine != null) {
                        if (inputLine.equals("RESET")) {
                            System.out.println("Disconnected");
                            robotController.interrupt();
                            break connection;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public static void SendCommand (RobotEvent event){
        System.out.println(event.toString()+ java.lang.System.currentTimeMillis());
        out.println(event.toString());
    }
}
