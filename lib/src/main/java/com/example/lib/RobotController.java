package com.example.lib;

import java.util.ArrayList;

public class RobotController extends Thread{
    static LinearOpMode linearOpMode;
    static boolean eventsInUse;
    public static ArrayList<RobotEvent> robotEvents;
    public static void OpenEventList(){
        eventsInUse = true;
    }
    public static void CloseEventList(){
        eventsInUse = false;
    }
    public static void setLinearOpMode(LinearOpMode opMode){ //use annotations
        linearOpMode=opMode;
    }
    public static void AddEvent(RobotAction action, String[] args){
        RobotController.WaitForEventAccess();
        OpenEventList();
        robotEvents.add(new RobotEvent(action, args));
        CloseEventList();
    }
    public static void WaitForEventAccess(){
        while (eventsInUse) { //waits until the command can be sent
            try{
                Thread.sleep(0);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
    public void run(){
        robotEvents = new ArrayList<RobotEvent>();
        try {
            linearOpMode.runOpMode();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
