package org.firstinspires.ftc.teamcode.TeamCode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp
public class TestAprilTag extends LinearOpMode {

    private AprilTagReader aprilTag = new AprilTagReader();




    @Override
    public void runOpMode() throws InterruptedException {

        aprilTag.initAprilTagCamera(hardwareMap, "Webcam 1", telemetry);

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("======================", " ");
            telemetry.addData("X: ",aprilTag.readTag().line.x.value());
            telemetry.addData("Y: ",aprilTag.readTag().line.y.value());
            telemetry.addData("Heading: ",aprilTag.readTag().angle.value());
            telemetry.update();

        }

    }
}
