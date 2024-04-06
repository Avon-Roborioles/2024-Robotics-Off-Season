package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class DroneLaunchSubsystem extends SubsystemBase {

    private final ServoEx servo;

    public DroneLaunchSubsystem(HardwareMap hardwareMap){
        this.servo = new SimpleServo(hardwareMap,"droneLauncher",0,330);
    }

    public void launch(){
        servo.setPosition(.25);
    }
}
