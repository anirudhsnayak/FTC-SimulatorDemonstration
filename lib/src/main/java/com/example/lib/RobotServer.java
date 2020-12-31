package com.example.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
                while (true) {
                    inputLine = in.readLine();
                    if (inputLine != null) {
                        if (inputLine.equals("RESET")) {
                            System.out.println("Disconnected");
                            robotController.interrupt();
                            break;
                        }
                        if (inputLine.startsWith("S;")){
                            ProcessSensorInput(inputLine.substring(2));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    void ProcessSensorInput(String input){
        int semiCount=0;
        int index=-1;
        String sensorIdentifier = "";
        String tag = "";
        double value = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ';'){
                String substring = input.substring(index+1, i);
                switch (semiCount){
                    case 0:
                        sensorIdentifier = substring;
                        break;
                    case 1:
                        tag = substring;
                        break;
                    case 2:
                        value = Double.parseDouble(substring);;
                        break;
                }
                index = i;
                semiCount++;
            }
        }
        System.out.println(sensorIdentifier);
        System.out.println(tag);
        System.out.println(value);
        switch(sensorIdentifier){
            case "0":
                DeviceMapping.DSValues.put(tag, value);
                break;
        }
    }
    public static void SendCommand (RobotEvent event){
        System.out.println(event.toString()+ java.lang.System.currentTimeMillis());
        out.println(event.toString());
    }
}
