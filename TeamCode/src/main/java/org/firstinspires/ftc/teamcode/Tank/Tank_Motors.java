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
    private double slowdrive;
    public void init_drive(HardwareMap map, String name, String name1,String name2,String name3) {
        leftFront = map.get(DcMotor.class,name);
        leftBack = map.get(DcMotor.class,name1);
        RightFront = map.get(DcMotor.class,name2);
        RightBack = map.get(DcMotor.class,name3);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        RightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        RightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void run_drive(Gamepad gamepad) {
        double leftstick = gamepad.left_stick_y;
        double rightstick = gamepad.right_stick_y;
        double leftpower = leftstick * slowdrive;
        double rightpower = rightstick * slowdrive;
        if (gamepad.a) {
            slowdrive = 0.35;
        } else{
            slowdrive = 1;
    }

        leftFront.setPower(leftpower);
        leftBack.setPower(leftpower);
        RightFront.setPower(rightpower);
        RightBack.setPower(rightpower);
    }
}
