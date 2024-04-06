package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangSubsystem extends SubsystemBase {

    private ServoEx servo;
    private double hangPos = 0.55;

    public HangSubsystem(HardwareMap hardwareMap){
        servo = new SimpleServo(hardwareMap,"hangServo",0,330);

    }

    public void hang(){
        servo.setPosition(.55);
    }

    public void cancel(){
        servo.setPosition(0);
    }

    public boolean isAtHangPos(){
        return hangPos == servo.getPosition();
    }
}
