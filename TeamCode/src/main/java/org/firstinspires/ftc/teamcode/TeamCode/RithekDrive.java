package org.firstinspires.ftc.teamcode.TeamCode;

import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.RobotConfig;
import org.opencv.core.Mat;


public class RithekDrive {


    private DcMotor frontLeft,frontRight,backLeft, backRight;


    public void init(HardwareMap hardwareMap){

        frontLeft = hardwareMap.get(DcMotor.class, RobotConfig.frontLeftName);
        frontRight = hardwareMap.get(DcMotor.class, RobotConfig.frontRightName);
        backLeft = hardwareMap.get(DcMotor.class, RobotConfig.backLeftName);
        backRight = hardwareMap.get(DcMotor.class, RobotConfig.backRightName);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    /**
     *
     * @param vector
     * @param turn - positive is left, and negative is right
     */
    public void inputVectorHeading(Vector2d vector, double turn){
        //Front Left and Back Right go up and right
        //Front Right and Back Left go up and left w
        double x = vector.x;
        double y = vector.y;

        double magnitude = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        double angle = Math.atan(y/x) - Math.toRadians(45);


    }
}
















