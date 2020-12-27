package com.example.lib;

import java.util.concurrent.TimeUnit;

public abstract class LinearOpMode { //need to increase the functionality, beta class for now
    static HardwareMap hardwareMap = new HardwareMap();

    abstract public void runOpMode() throws InterruptedException;

    static void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public final void idle() {
        Thread.yield();
    }

}
