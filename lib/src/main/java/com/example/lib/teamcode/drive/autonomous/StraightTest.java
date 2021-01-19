package com.example.lib.teamcode.drive.autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.example.lib.robotcore.*;

import com.example.lib.teamcode.drive.MecanumDrivetrain;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Autonomous
public class StraightTest extends LinearOpMode {
    public static double DISTANCE = 600; // in

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrivetrain drive = new MecanumDrivetrain(hardwareMap);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(trajectory);

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
