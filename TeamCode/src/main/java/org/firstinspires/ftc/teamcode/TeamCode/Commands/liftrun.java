package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.LiftSubsystem15;

public class liftrun extends CommandBase {
    private LiftSubsystem15 lift;
    public liftrun(LiftSubsystem15 lifter){
        lift = lifter;
        addRequirements(lift);
    }
    @Override
    public void execute(){
        lift.liftup();
    }
    @Override
    public boolean isFinished() {return true;}
}
