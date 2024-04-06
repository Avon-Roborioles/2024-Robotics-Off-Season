package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.opencv.core.Point;

public class PWMSimpleServo  {


    private final ServoImplEx servo;

    //physical limits of the servo

    //limits that we set
    private double setMaxPwm;
    private double setMinPwm;
    //angles are in degrees
    double physMaxAngle=360;
    double physMinAngle=0;

    //assuptions don't make the math bad
    //assume that this is the lower point
    private Point servoAng1 = new Point();

    //assume that this is the higher point
    private Point servoAng2 = new Point();



    /**
     * You have to use the setPwmToAngle() method to set the pwm to angle conversions up, not the constructor.
     * Use the setServoRange() to set custom limits. The units for that are in PWM.
     * @param hardwareMap - the hardware map
     * @param name - name of the device in the driver hub config
     * @param physMaxPwm - physical upper limit of the servo
     * @param physMinPwm - physical lower limit of the servo
     */
    public PWMSimpleServo(HardwareMap hardwareMap, String name, double physMaxPwm, double physMinPwm){


        servo = hardwareMap.get(ServoImplEx.class, name);

        setMaxPwm = physMaxPwm;
        setMinPwm = physMinPwm;
        servo.setPwmRange(new PwmControl.PwmRange(physMinPwm,physMaxPwm));
    }

    /**
     * These units are in PWM, not angle.
     * @param setMinPwm
     * @param setMaxPwm
     */
    public void setServoRange(double setMinPwm, double setMaxPwm){
        this.setMinPwm = setMinPwm;
        this.setMaxPwm = setMaxPwm;
    }

    public void setServoToAngle(double pwm1, double angle1, double pwm2, double angle2 ){
        servoAng1.set(new double[]{pwm1,angle1});
        servoAng2.set(new double[]{pwm2,angle2});

    }
    public void setPosition(double pos){servo.setPosition(pos);}
    public double getPosition(){return servo.getPosition();}
    public double angleToServo(double angle, AngleUnit angleUnit){

        double degAngle = angle;
        switch (angleUnit){
            case RADIANS:
                degAngle  = angle / Math.PI *180;
                break;
        }
        double servo = Range.scale(angle,servoAng1.y,servoAng2.y,servoAng1.x,servoAng2.x);
        return servo;
    }
    public double servoToAngle(double servoPos, AngleUnit angleUnit){
        double degAngle= Range.scale(servoPos,servoAng1.x,servoAng2.x,servoAng1.y,servoAng2.y);
        switch (angleUnit) {
            case RADIANS:
                return degAngle/ Math.PI *180;
            default:
                return degAngle;
        }
    }

    public void setToDegreeAngle(double angle){
        servo.setPosition(Range.scale(angle,servoAng1.y,servoAng2.y,servoAng1.x,servoAng2.x)%360);
    }

    public double getDegreeAngle(){
        return servoToAngle(servo.getPosition(), AngleUnit.DEGREES);
    }
}
