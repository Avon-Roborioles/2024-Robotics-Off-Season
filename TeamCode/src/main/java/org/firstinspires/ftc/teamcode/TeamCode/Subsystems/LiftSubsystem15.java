package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftSubsystem15 extends SubsystemBase {

    private final DcMotor lift;
    private final double liftspeed = 0.4;
    public LiftSubsystem15(final HardwareMap hardwareMap, final String name) {
        lift = hardwareMap.get(DcMotor.class, name);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void liftup(){lift.setPower(-liftspeed);}
    public void liftdown(){lift.setPower(liftspeed);}
    public void liftoff(){lift.setPower(0);}
}

