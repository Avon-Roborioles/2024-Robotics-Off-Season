package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;


public class HandRotSubsystem extends SubsystemBase {
    private final ServoEx handRot;
    private final DoubleSupplier scoreControl;
    private double targetPos;
    private Telemetry telemetry;


    public HandRotSubsystem(HardwareMap hardwareMap, DoubleSupplier control, Telemetry telemetry){
        handRot = new SimpleServo(hardwareMap, "handRotate", 0,330);
        scoreControl = control;
        targetPos = handRot.getPosition();
        this.telemetry = telemetry;
    }


    public void operate(){
        targetPos =Range.clip(targetPos+scoreControl.getAsDouble()*.05,0,330);
        handRot.setPosition(targetPos);
        telemetry.addData("Hand Rotation Target Position", targetPos);
        telemetry.addData("Hand Rotation Real Position",handRot.getPosition());

    }
    public boolean isAtTarget(){return handRot.getPosition() == targetPos;}
    public void grab(){
        handRot.setPosition(0);
    }
    public double getServoPos(){return handRot.getPosition();}
    public boolean isAtZero(){
        return handRot.getPosition()==0;
    }
}
