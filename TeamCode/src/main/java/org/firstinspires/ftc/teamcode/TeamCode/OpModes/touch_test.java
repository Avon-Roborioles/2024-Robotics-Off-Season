package org.firstinspires.ftc.teamcode.TeamCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class touch_test extends LinearOpMode {

    private final org.firstinspires.ftc.teamcode.TeamCode.OpModes.touch_sensor Touch = new touch_sensor();

    public void runOpMode() throws InterruptedException {
        touch_sensor.initTouch(hardwareMap);

        waitForStart();


        while (opModeIsActive()) {
            touch_sensor.runTouchBackboard();
            touch_sensor.getTelemetry(telemetry);
            telemetry.update();
        }
    }
}
