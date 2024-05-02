package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem14;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;

public class Intake15 extends CommandBase {
    private final IntakeSubsystem15 m_intakeSubsystem15;

    public Intake15(IntakeSubsystem15 intakeSubsystem15){
        m_intakeSubsystem15 = intakeSubsystem15;
        addRequirements(m_intakeSubsystem15);
    }


    public void intake15run(){
        m_intakeSubsystem15.intake15run();
    }


    public void intakeend15(boolean interrupted){
        m_intakeSubsystem15.stop15();
    }
}
