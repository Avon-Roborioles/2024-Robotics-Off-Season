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
        while (opModeIsActive()&&!isStopRequested()) {

            aprilTag.readTag();

        }

    }
}
