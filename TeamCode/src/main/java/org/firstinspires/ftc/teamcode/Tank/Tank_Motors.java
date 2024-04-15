package org.firstinspires.ftc.teamcode.Tank;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Tank_Motors {
private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor RightFront = null;
    private DcMotor RightBack = null;
    public void init_drive(HardwareMap map, String name, String name1,String name2,String name3) {
        leftFront = map.get(DcMotor.class,name);
        leftBack = map.get(DcMotor.class,name1);
        RightFront = map.get(DcMotor.class,name2);
        RightBack = map.get(DcMotor.class,name3);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        RightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void run_drive(Gamepad gamepad){
        double leftpower = gamepad.left_stick_y;
        double rightpower = gamepad.right_stick_y;
        leftFront.setPower(leftpower);
        leftBack.setPower(leftpower);
        RightFront.setPower(rightpower);
        RightBack.setPower(rightpower);
    }
}
