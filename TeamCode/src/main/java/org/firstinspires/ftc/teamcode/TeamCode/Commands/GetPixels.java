package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

public class GetPixels extends CommandBase {
    private IntakeSubsystem15 intake;
    private  PassSubsystem15 pass;
    public GetPixels(IntakeSubsystem15 intaker, PassSubsystem15 passr){
        intake = intaker;
        pass = passr;
        addRequirements(intake);
        addRequirements(pass);
    }

  @Override
    public void execute(){
        intake.intake15run();
        pass.Pass15();

    }
    @Override
    public boolean isFinished() {
        return true;
    }



}
