package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem15 extends SubsystemBase {

    private final DcMotor intake;
    private final double intakeSpeed = 1;
    public IntakeSubsystem15(final HardwareMap hardwareMap, final String name) {
        intake = hardwareMap.get(DcMotor.class, name);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
        public void intake15(){
            intake.setPower(intakeSpeed);
        }

        public void outtake15(){
            intake.setPower(-intakeSpeed);
        }

        public void stop15(){
            intake.setPower(0);
        }
    }


