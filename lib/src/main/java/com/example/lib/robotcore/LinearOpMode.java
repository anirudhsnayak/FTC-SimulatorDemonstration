package com.example.lib.robotcore;

import com.example.lib.server.RobotAction;
import com.example.lib.server.RobotEvent;
import com.example.lib.server.RobotServer;

public abstract class LinearOpMode { //TODO: MAKE THIS WORK WITH ANNOTATIONS
    //need to increase the functionality, beta class for now
    protected static HardwareMap hardwareMap = new HardwareMap();
    boolean isStarted = true; //TODO: MAKE THIS FALSE, THEN SET ON START
    boolean stopRequested = false;
    abstract public void runOpMode() throws InterruptedException;

    protected void sleep(long milliseconds)  {
        CloseEventPackage();
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            Thread.currentThread().interrupt();
        }
    }
    public final void idle() {
        CloseEventPackage();
        Thread.yield();
    }

    public final void waitForStart(){
        //TODO: ADD DETAILS HERE
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
    public static void CloseEventPackage(){ //probably shouldn't be visible to user code
        RobotServer.SendCommand(new RobotEvent(RobotAction.IDLING, new String[]{}));
    }
}
