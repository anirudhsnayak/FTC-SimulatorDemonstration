package com.fastandcurious.ftcsim.teamcode.autonomous;


import com.fastandcurious.ftcsim.teamcode.MecanumDrivetrain;
import com.fastandcurious.ftcsim.robotcore.LinearOpMode;

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
