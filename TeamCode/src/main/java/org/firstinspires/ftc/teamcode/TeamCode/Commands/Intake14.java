package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem14;

public class Intake14 extends CommandBase {

    private final IntakeSubsystem14 m_intakeSubsystem;

    public Intake14(IntakeSubsystem14 intakeSubsystem14){
        m_intakeSubsystem = intakeSubsystem14;
        addRequirements(m_intakeSubsystem);
    }

    @Override
    public void execute(){
        m_intakeSubsystem.intake();
    }

    @Override
    public void end(boolean interrupted){
        m_intakeSubsystem.stop();
    }



}
