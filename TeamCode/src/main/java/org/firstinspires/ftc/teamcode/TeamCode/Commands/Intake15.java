package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem14;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;

public class Intake15 extends CommandBase {
    private final IntakeSubsystem15 m_15intakeSubsystem;

    public Intake15(IntakeSubsystem15 intakeSubsystem15){
        m_15intakeSubsystem = intakeSubsystem15;
        addRequirements(m_15intakeSubsystem);
    }

    @Override
    public void execute15(){
        m_15intakeSubsystem.intake15run();
    }

    @Override
    public void end15(boolean interrupted){
        m_15intakeSubsystem.stop15();
    }
}
