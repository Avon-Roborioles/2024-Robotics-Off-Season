package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outake_Subsystem15 extends SubsystemBase {
    private final CRServo outake;
    private final double outakespeed = 1;
    private RevTouchSensor touch3;
    public Outake_Subsystem15(final HardwareMap hardwareMap, final String name, final String name1){
        outake = hardwareMap.get(CRServo.class, name);
        touch3 = hardwareMap.get(RevTouchSensor.class, name1);
    }
    public boolean touch() {
        if (touch3.isPressed()) {
            return true;
        } else {
            return false;
        }
    }
    public void outake15() {outake.setPower(outakespeed);}
    public void outakestop() {outake.setPower(0);}
}
