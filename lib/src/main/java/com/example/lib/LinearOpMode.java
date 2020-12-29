package com.example.lib;

import java.util.concurrent.TimeUnit;

public abstract class LinearOpMode { //TODO: MAKE THIS WORK WITH ANNOTATIONS
    //need to increase the functionality, beta class for now
    static HardwareMap hardwareMap = new HardwareMap();
    boolean isStarted = true; //TODO: MAKE THIS FALSE, THEN SET ON START
    boolean stopRequested = false;
    abstract public void runOpMode() throws InterruptedException;

    void sleep(long milliseconds)  {
        RobotServer.SendCommand(new RobotEvent(RobotAction.IDLING, new String[]{}));
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            Thread.currentThread().interrupt();
        }
    }
    public final void idle() {
        Thread.yield();
    }

    public final boolean opModeIsActive() {
        boolean isActive = !this.isStopRequested() && this.isStarted();
        if (isActive) {
            idle();
        }
        return isActive;
    }


    public final boolean isStarted() {
        return this.isStarted || Thread.currentThread().isInterrupted();
    }


    public final boolean isStopRequested() {
        return this.stopRequested || Thread.currentThread().isInterrupted();
    }

}
