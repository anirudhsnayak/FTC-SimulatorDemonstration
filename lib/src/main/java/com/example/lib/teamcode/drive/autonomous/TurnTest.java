package com.example.lib.teamcode.drive.autonomous;

import com.example.lib.robotcore.*;

import com.example.lib.teamcode.drive.MecanumDrivetrain;

/*
 * This is a simple routine to test turning capabilities.
 */

public class TurnTest extends LinearOpMode {
    public static double ANGLE = 90; // deg

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrivetrain drive = new MecanumDrivetrain(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));
    }
}
