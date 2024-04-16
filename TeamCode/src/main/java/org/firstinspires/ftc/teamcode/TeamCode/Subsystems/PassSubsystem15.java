package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PassSubsystem15 {
    private final DcMotor pass;
    private final double intakeSpeed = 1;
    public PassSubsystem15(final HardwareMap hardwareMap, final String name) {
        pass = hardwareMap.get(DcMotor.class, name);
        pass.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void Pass15(){
        pass.setPower(intakeSpeed);
    }

    public void BackPass15(){
        pass.setPower(-intakeSpeed);
    }

    public void stoppass(){
        pass.setPower(0);
    }
}
