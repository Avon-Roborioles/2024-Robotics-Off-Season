package org.firstinspires.ftc.teamcode.TeamCode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Tank.Tank_Motors;

@TeleOp
public class Tank_Drive extends LinearOpMode {
    private final org.firstinspires.ftc.teamcode.Tank.Tank_Motors drive = new Tank_Motors();
 public void runOpMode() throws InterruptedException {
     drive.init_drive(hardwareMap, "left_front","left_back","right_front", "right_back");
     waitForStart();
     while (opModeIsActive()) {
         drive.run_drive(gamepad1);
     }
 }
}
