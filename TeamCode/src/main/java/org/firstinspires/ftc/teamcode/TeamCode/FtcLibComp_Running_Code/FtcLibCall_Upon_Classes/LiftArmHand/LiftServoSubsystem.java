package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class LiftServoSubsystem extends SubsystemBase {
    private final ServoImplEx liftServo;




    //Servo lift variables !!!!!!! This is a different servo and needs to be set to a different PWM Range !!!!!!!
    private final PwmControl.PwmRange servoLiftRange = new PwmControl.PwmRange(799, 1500);
    private double servoLiftPosition = 0; //zero with 0 as the arm at the intake
    private final double servoLiftButtonDeadZone = 0.15;
    private final double servoLiftSensitivity = 2.25;
    private final double armServoGearRatio = 2;
    private double servoLiftPositionPwm;

    //Servo lift limit
    private final double servoLiftMaxLimitPWM = 0.20; //servo limit opposite of intake

    //End Arm variables
    private double endArmServoPosition; //zero at points toward down arm toward bot and straight up is +
    private final double endArmIntakePosition = 212;
    private double endArmServoPositionPWM;
    private final double endArmStartPositionDegrees = 30;

    //Servo lift to end arm math variables separate form normal operation
    private final double servoLiftMaxLimitDegrees = 230;
    private final double endArmMaxLimitDegrees = 270;
    private final double servoLiftVerticalPosition =94;
    private double servoOffsetFactor1 = -10;
    private DoubleSupplier servoOffset = () -> servoOffsetFactor1 + endArmStartPositionDegrees;//change to adjust until it is at the 30 degree angle smaller = more angle



    private final DoubleSupplier control;




    private final Telemetry telemetry;


    private ServoEx endArmServo;
    public static final double endArmServoGrabAngle = 212;





    public LiftServoSubsystem(HardwareMap hardwareMap, DoubleSupplier scoreControl, Telemetry telemetry) {

        liftServo = hardwareMap.get(ServoImplEx.class,"servoLiftServo");
        endArmServo = new SimpleServo(hardwareMap, "endArmServo", 0, 330);
        liftServo.setPwmRange(servoLiftRange);

        control = scoreControl;

        this.telemetry = telemetry;
    }
    public void buttonedGrab(){
        double tempServoLiftPosition = 0;
        double tempServoLiftPositionPwm = 1-(((tempServoLiftPosition*armServoGearRatio)* (1 / servoLiftMaxLimitDegrees)) * servoLiftMaxLimitPWM);

        double tempEASPos = endArmIntakePosition;
        double tempEASPosPwm = (tempEASPos * (1 / endArmMaxLimitDegrees));
        setServoPWMPos(tempServoLiftPositionPwm,tempEASPosPwm);
        telemetry();

    }
    public void manualGrab(){
        if (control.getAsDouble() > servoLiftButtonDeadZone || control.getAsDouble() < -servoLiftButtonDeadZone) {
            servoLiftPosition += control.getAsDouble() *servoLiftSensitivity;
        }

        if (servoLiftPosition> (servoLiftVerticalPosition - 87)){
            endArmServoPosition = endArmIntakePosition + servoLiftPosition ;
        } else {
            endArmServoPosition = endArmIntakePosition;

        }


        servoLiftPosition   = Range.clip(servoLiftPosition,0,servoLiftMaxLimitDegrees);
        endArmServoPosition = Range.clip(endArmServoPosition,0,endArmMaxLimitDegrees);

        setPWMPos(servoLiftPosition,endArmServoPosition);
        endArmServo.setPosition(endArmServoPositionPWM);
        liftServo.setPosition(servoLiftPositionPwm);
        telemetry();

    }
    public void setToScore(){

        if (control.getAsDouble() > servoLiftButtonDeadZone || control.getAsDouble() < -servoLiftButtonDeadZone) {
            servoLiftPosition += control.getAsDouble() *servoLiftSensitivity;
        }

        endArmServoPosition = servoOffset.getAsDouble()+ (servoLiftPosition - servoLiftVerticalPosition);

        servoLiftPosition   = Range.clip(servoLiftPosition,0,servoLiftMaxLimitDegrees);
        endArmServoPosition = Range.clip(endArmServoPosition,0,endArmMaxLimitDegrees);

        setPWMPos(servoLiftPosition,endArmServoPosition);

        setServoPWMPos();
        telemetry();

    }
    public void setToScoreSetPos(double pos){
        servoLiftPosition= pos;
        if (servoLiftPosition > servoLiftMaxLimitDegrees){
            servoLiftPosition = servoLiftMaxLimitDegrees;
        }else if (servoLiftPosition < 0){
            servoLiftPosition = 0;
        }


        if (endArmServoPosition > endArmMaxLimitDegrees){
            endArmServoPosition = endArmMaxLimitDegrees;
        }else if (endArmServoPosition < 0){
            endArmServoPosition = 0;
        }
        endArmServoPosition = servoOffset.getAsDouble()+ (servoLiftPosition - servoLiftVerticalPosition);
        servoLiftPositionPwm = 1-(((servoLiftPosition*armServoGearRatio)* (1 / servoLiftMaxLimitDegrees)) * servoLiftMaxLimitPWM);
        endArmServoPositionPWM = (endArmServoPosition * (1 / endArmMaxLimitDegrees));

        endArmServo.setPosition(endArmServoPositionPWM);
        liftServo.setPosition(servoLiftPositionPwm);
        telemetry();
    }
    public void setToHang() {
        setToScoreSetPos(servoLiftVerticalPosition+30);
        telemetry();
    }

    public void operate(){
        if (servoLiftPosition < servoLiftVerticalPosition){
            manualGrab();
        } else {
            setToScore();
        }
    }
    public void setPWMPos(double liftServoPos, double endArmServoPos){
        servoLiftPositionPwm = 1-(((liftServoPos*armServoGearRatio)* (1 / servoLiftMaxLimitDegrees)) * servoLiftMaxLimitPWM);
        endArmServoPositionPWM = (endArmServoPos * (1 / endArmMaxLimitDegrees));
    }

    public void setServoPWMPos(){
        setServoPWMPos(servoLiftPositionPwm,endArmServoPositionPWM);


    }

    public void setServoPWMPos(double liftServoPos,double endArmPos){
        liftServo.setPosition(liftServoPos);
        endArmServo.setPosition(endArmPos);

    }



    public boolean isAtGrabPos(){return liftServo.getPosition() == 0 && endArmServo.getPosition() == endArmIntakePosition;}

    public boolean isAtScorePos(){return isSetFinished();}
    public boolean isAtOperatePos(){
        return  isSetFinished();
    }

    public boolean isSetFinished() {return liftServo.getPosition() == servoLiftPositionPwm&&endArmServo.getPosition()==endArmServoPositionPWM;}


    public boolean isGrabFinished() {return liftServo.getPosition() == 0 &&endArmServo.getPosition() == endArmIntakePosition;}
    public void telemetry(){
        telemetry.addData("Lift Servo Set Pos (In pwm)",servoLiftPositionPwm);
        telemetry.addData("End Arm Servo Set Pos (In pwm)",endArmServoPositionPWM);
        telemetry.addData("Control*sensitivity",control.getAsDouble()*servoLiftSensitivity);
    }


}
