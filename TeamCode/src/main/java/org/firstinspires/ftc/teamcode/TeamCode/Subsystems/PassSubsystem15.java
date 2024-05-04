package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PassSubsystem15 extends SubsystemBase {
    private final DcMotor pass;
    private final double intakeSpeed = 1;
    private RevTouchSensor toucher2;
    public PassSubsystem15(final HardwareMap hardwareMap, final String name, final String name1) {
        pass = hardwareMap.get(DcMotor.class, name);
        toucher2 = hardwareMap.get(RevTouchSensor.class, name1);
        pass.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    public boolean touchsensor2() {
        if (toucher2.isPressed()) {
            return true;
        } else {
            return false;
        }
    }
    public void Pass15(){
        pass.setPower(-intakeSpeed);
    }

    public void BackPass15(){
        pass.setPower(-intakeSpeed);
    }

    public void stoppass(){
        pass.setPower(0);
    }
}
