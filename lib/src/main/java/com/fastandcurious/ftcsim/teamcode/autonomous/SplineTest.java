package com.fastandcurious.ftcsim.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import com.fastandcurious.ftcsim.robotcore.Autonomous;
import com.fastandcurious.ftcsim.teamcode.MecanumDrivetrain;
import com.fastandcurious.ftcsim.robotcore.LinearOpMode;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous
public class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrivetrain drive = new MecanumDrivetrain(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(60, 60), 0)
                .build();

        drive.followTrajectory(traj);

        sleep(2000);

        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), true)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(180))
                        .build()
        );
        drive.setMotorPowers(0,0,0,0);
    }
}
