package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.rev.RevTouchSensor;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

public class GetPixels extends CommandBase {
    private IntakeSubsystem15 intake;
    private PassSubsystem15 pass;


    public GetPixels(IntakeSubsystem15 intaker) {

        intake = intaker;

        addRequirements(intake);
        addRequirements(pass);
    }

    @Override
    public void execute() {
        intake.intake15run();
    }


    @Override
    public boolean isFinished() {
        if (intake.touchsensor()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop15();
    }
}