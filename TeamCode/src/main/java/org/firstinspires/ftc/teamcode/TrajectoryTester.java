package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@Autonomous(name="Trajectory Tester")
public class TrajectoryTester extends LinearOpMode {

    public static final Pose2d START_POSE = new Pose2d(11.095, 60.9, Math.toRadians(-90.0));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(START_POSE);
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(START_POSE);

        TrajectorySequence trajectory = builder
                .lineToLinearHeading(new Pose2d(11.257160871205839, 37.913110091622165, Math.toRadians(270.4468808437878)))
                .lineToLinearHeading(new Pose2d(12.38021638397941, 11.599133340164073, Math.toRadians(181.05629657511355)))
                .lineToConstantHeading(new Vector2d(-56.80808744304193, 8.53273232382864))
                .lineToConstantHeading(new Vector2d(8.686741837583687, 8.334917291098382))
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(37.9388205561116, 32.792221882921055, Math.toRadians(0.055139329915564854)),Math.toRadians(0.055139329915564854))
                .setReversed(false)
                .build();

        waitForStart();

        drive.followTrajectorySequenceAsync(trajectory);
        while(opModeIsActive()) {
            if(drive.isBusy()) {
                drive.update();
            }

            telemetry.update();
        }
    }
}
