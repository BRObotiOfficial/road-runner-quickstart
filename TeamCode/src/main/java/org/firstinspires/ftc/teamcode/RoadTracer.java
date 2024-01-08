package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;

import java.util.ArrayList;
import java.util.List;

//uwu bwoboti
@Config()
@TeleOp(name = "RoadTracer")
public class RoadTracer extends LinearOpMode {
    public static final Pose2d START_POSE = TrajectoryTester.START_POSE;
    private StandardTrackingWheelLocalizer localizer;
    private List<String> path = new ArrayList<>();

    private KeyState splineToKey = new KeyState();
    private KeyState lineToKey = new KeyState();
    private KeyState turnKey = new KeyState();

    private Pose2d lastPose = START_POSE;

    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        localizer = new StandardTrackingWheelLocalizer(hardwareMap, new ArrayList<>(), new ArrayList<>());
        localizer.setPoseEstimate(START_POSE);
        waitForStart();

        while(opModeIsActive()){
            splineToKey.update(gamepad1.a);
            lineToKey.update(gamepad1.b);
            turnKey.update(gamepad1.x);

            localizer.update();
            if(splineToKey.isKeyJustPressed()){
                createSpline(localizer.getPoseEstimate());
            }
            if(lineToKey.isKeyJustPressed()){
                createLine(localizer.getPoseEstimate());
            }
            if(turnKey.isKeyJustPressed()){
                createTurn(localizer.getPoseEstimate());
            }

            if(splineToKey.isKeyPressed()) {
                telemetry.addLine("Making spline");
            }

            if(lineToKey.isKeyPressed()) {
                telemetry.addLine("Making line");
            }

            if(turnKey.isKeyPressed()) {
                telemetry.addLine("Making turn");
            }

            for(String line: path){
                telemetry.addLine(line);
            }

            telemetry.update();
        }
    }
    public void createSpline(Pose2d to){
        StringBuilder builder = new StringBuilder();
        if(Math.abs(to.getHeading() - lastPose.getHeading()) > Math.toRadians(2.0)){
            builder
                    .append(".splineToLinearHeading(new Pose2d(")
                    .append(to.getX())
                    .append(", ")
                    .append(to.getY())
                    .append(", ")
                    .append("Math.toRadians(")
                    .append(Math.toDegrees(to.getHeading()))
                    .append(")),Math.toRadians(")
                    .append(Math.toDegrees(to.getHeading()))
                    .append("))")
            ;
            path.add(builder.toString());
        }
        else {
            builder
                    .append(".splineToConstantHeading(new Vector2d(")
                    .append(to.getX())
                    .append(", ")
                    .append(to.getY())
                    .append("),Math.toRadians(")
                    .append(Math.toDegrees(to.getHeading()))
                    .append("))")
            ;
            path.add(builder.toString());
        }

        lastPose = to;
    }
    public void createLine(Pose2d to){
        StringBuilder builder = new StringBuilder();
        if(Math.abs(to.getHeading() - lastPose.getHeading()) > Math.toRadians(2.0)){
            builder
                    .append(".lineToLinearHeading(new Pose2d(")
                    .append(to.getX())
                    .append(", ")
                    .append(to.getY())
                    .append(", ")
                    .append("Math.toRadians(")
                    .append(Math.toDegrees(to.getHeading()))
                    .append(")))")
            ;
            path.add(builder.toString());
        }
        else {
            builder
                    .append(".lineToConstantHeading(new Vector2d(")
                    .append(to.getX())
                    .append(", ")
                    .append(to.getY())
                    .append("))")
            ;
            path.add(builder.toString());
        }

        lastPose = to;
    }
    public void createTurn(Pose2d to){
        StringBuilder builder = new StringBuilder();
        builder
                .append(".turn(Math.toRadians(")
                .append(Math.toDegrees(to.getHeading()))
                .append("))")
        ;
        path.add(builder.toString());

        lastPose = to;
    }
}
