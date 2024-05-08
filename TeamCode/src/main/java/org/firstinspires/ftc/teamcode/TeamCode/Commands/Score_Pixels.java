package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.Outake_Subsystem15;

public class Score_Pixels extends CommandBase {
    private Outake_Subsystem15 outake;
    public Score_Pixels(Outake_Subsystem15 scorer) {
        outake = scorer;
    addRequirements(outake);
    }
    @Override
    public void execute() { outake.outake15();}
    @Override
    public boolean isFinished() {return true;}

}
