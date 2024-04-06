package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.BooleanSupplier;


public class HandOpen extends SubsystemBase {

    private ServoEx servo;
    private BooleanSupplier control;
    //True means grabbed and False means closed

    private double grabServoRotMin = .2;
    private double grabServoRotMax = grabServoRotMin+.24242424242424;

    public HandOpen(HardwareMap hardwareMap, BooleanSupplier control){

        servo = new SimpleServo(hardwareMap, "grabServo", 0,360);
        this.control = control;
    }
    

    public void grab(){
        servo.setPosition(grabServoRotMin);
    }
    public void open(){
        servo.setPosition(grabServoRotMax);
    }
    public void driverOperate(){
        if (control.getAsBoolean()){
            grab();
        } else {
            open();
        }
    }
    public boolean isOpen(){
        return servo.getPosition() == grabServoRotMax;
    }
    public boolean isClosed(){
        return servo.getPosition() == grabServoRotMin;
    }

    public boolean isFinished(){
        if (control.getAsBoolean()) {
            return isClosed();
        }
            return isOpen();

    }
}
