package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class ArmSubsystem extends SubsystemBase {
    private final ServoImplEx servoLiftServo;
    private final Servo endArmServo;


    PwmControl.PwmRange servoLiftRange = new PwmControl.PwmRange(799, 1500);
    private double servoLiftPosition = 0;
    private double servoLiftButton;
    private double servoLiftButtonDeadZone = 0.15;
    private double servoLiftSensitivity = 2.25;
    private double armServoGearRatio = 2;
    private double servoLiftPositionPwm;
    private double servoLiftMaxLimitPWM = 0.20;
    private double endArmServoPosition;
    private double endArmIntakePosition = 212;
    private double endArmServoPositionPWM;
    private double endArmStartPositionDegrees = 30;
    private double servoLiftMaxLimitDegrees = 230;
    private double endArmMaxLimitDegrees = 270;
    private double servoLiftVerticalPosition =94;
    private double servoOffsetFactor1 = -10;
    private double servoOffset = servoOffsetFactor1 + endArmStartPositionDegrees;
    private double currentArmPosDeg;

    public ArmSubsystem(final HardwareMap hardwareMap, final String sLSName, final String eASName){
        servoLiftServo = hardwareMap.get(ServoImplEx.class, sLSName);
        servoLiftServo.setPwmRange(servoLiftRange);
        endArmServo = hardwareMap.get(Servo.class, eASName);
    }
    private double convertSLSDegToPWM(double sLSDeg){
        return 1-(((sLSDeg*armServoGearRatio)* (1 / servoLiftMaxLimitDegrees)) * servoLiftMaxLimitPWM);
    }
    private double sLSLimit(double input){
        if (input > servoLiftMaxLimitDegrees){
            input = servoLiftMaxLimitDegrees;
        }else if (input < 0){
            input = 0;
        }
        return input;
    }
    private double convertEASDegToPWM(double eASDeg){
        return (eASDeg * (1 / endArmMaxLimitDegrees));
    }
    private double eASLimit(double input){
        if (input > endArmMaxLimitDegrees){
            input = endArmMaxLimitDegrees;
        }else if (input < 0){
            input = 0;
        }
        return input;
    }
    private double getEndArmTargetPos(double sLSPos){
        if (sLSPos> (servoLiftVerticalPosition - 87)&& sLSPos < servoLiftVerticalPosition){
            endArmServoPosition = endArmIntakePosition + sLSPos -2  ;
        }else if (sLSPos > servoLiftVerticalPosition) {
            endArmServoPosition = servoOffset+ (sLSPos - servoLiftVerticalPosition) ;
        } else {
            endArmServoPosition = endArmIntakePosition;
        }
        return endArmServoPosition;
    }

    public void setFullArm(double armDeg) {
        currentArmPosDeg = sLSLimit(armDeg);
        servoLiftServo.setPosition(convertSLSDegToPWM(sLSLimit(armDeg)));
        endArmServo.setPosition(convertEASDegToPWM(eASLimit(getEndArmTargetPos(armDeg))));
    }
    public double getCurrentArmPosDeg(){
        return currentArmPosDeg;
    }



}
