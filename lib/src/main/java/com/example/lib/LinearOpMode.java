package com.example.lib;

import java.util.concurrent.TimeUnit;

public abstract class LinearOpMode { //need to increase the functionality, beta class for now
    static HardwareMap hardwareMap = new HardwareMap();

    abstract public void runOpMode() throws InterruptedException;

    static void sleep(long milliseconds) {
        RobotServer.SendCommand(new RobotEvent(RobotAction.IDLING, new String[]{}));
        try {
            Thread.currentThread().sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println(ex.toString());
        }
    }
    public final void idle() {
        Thread.currentThread().yield();
    }

}
