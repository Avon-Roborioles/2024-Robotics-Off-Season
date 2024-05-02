package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

public class Pass15 extends CommandBase {
    private final PassSubsystem15 m_pass15Subsystem15;

    public Pass15(PassSubsystem15 passSubsystem15){
        m_pass15Subsystem15 = passSubsystem15;
        addRequirements(m_pass15Subsystem15);
    }


    public void executepass15(){
        m_pass15Subsystem15.Pass15();
    }


    public void endpass15(boolean interrupted){
        m_pass15Subsystem15.stoppass();
    }
}
