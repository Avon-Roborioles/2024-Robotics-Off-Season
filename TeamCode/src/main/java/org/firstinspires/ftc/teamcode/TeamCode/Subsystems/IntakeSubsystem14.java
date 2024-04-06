package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem14 extends SubsystemBase {

    private final DcMotor intakeMotor;
    private final double intakeSpeed = 1;

    public IntakeSubsystem14(final HardwareMap hardwareMap, final String name){
       intakeMotor = hardwareMap.get(DcMotor.class, name);
       intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(){
        intakeMotor.setPower(intakeSpeed);
    }

    public void outtake(){
        intakeMotor.setPower(-intakeSpeed);
    }

    public void stop(){
        intakeMotor.setPower(0);
    }

}
