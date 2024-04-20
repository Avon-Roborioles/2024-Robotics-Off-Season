package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem extends SubsystemBase {
    private final Servo clawServo;
    private final double clawOpenPos = 0;
    private final double clawClosePos = 1;
    private double targetPosition = 0;

    public ClawSubsystem(final HardwareMap hardwareMap, final String name){
        clawServo = hardwareMap.get(Servo.class, name);
        clawServo.scaleRange(0,1);
        clawServo.setDirection(Servo.Direction.FORWARD);
    }

    public void open(){
        clawServo.setPosition(clawOpenPos);
        targetPosition = clawOpenPos;
    }
    public void close(){
        clawServo.setPosition(clawClosePos);
        targetPosition = clawClosePos;
    }
    public boolean isFinished(){
        if (clawServo.getPosition() == targetPosition){
            return true;
        }
        return false;
    }
}
