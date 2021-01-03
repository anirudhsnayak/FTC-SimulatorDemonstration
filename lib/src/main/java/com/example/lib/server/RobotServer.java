package com.example.lib.server;

import com.example.lib.robotcore.DeviceMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RobotServer extends Thread {
    static PrintWriter out;
    static HashMap<String, RobotEvent> eventTable = new HashMap<String, RobotEvent>();

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
        switch(sensorIdentifier){
            case "0":
                DeviceMapping.DSValues.put(tag, value);
                break;
        }
    }
    public static void SendCommand (RobotEvent event){
        if (event.eventId == 1) {
            eventTable.put("undefined", event); //just a placeholder
            StringBuilder builder = new StringBuilder();
            String output;
            for (RobotEvent _event: eventTable.values()) {
               builder.append(_event.toString()).append(System.lineSeparator());
            }
            output = builder.substring(0, Math.max(0, builder.length()-System.lineSeparator().length()));
            if (!output.equals("")){
                out.println(output);
            }
            eventTable.clear();
            return;
        }
        eventTable.put(event.eventArgs[0], event);
    }
}
