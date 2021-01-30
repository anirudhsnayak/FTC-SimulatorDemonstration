package com.fastandcurious.ftcsim.robotcore;

import com.fastandcurious.ftcsim.server.RobotAction;
import com.fastandcurious.ftcsim.server.RobotEvent;
import com.fastandcurious.ftcsim.server.RobotServer;

public abstract class LinearOpMode {
    protected static HardwareMap hardwareMap = new HardwareMap();
    boolean isStarted = true;
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
        //Does nothing for now
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
    public static void CloseEventPackage(){
        RobotServer.SendCommand(new RobotEvent(RobotAction.IDLING, new String[]{}));
    }
}
