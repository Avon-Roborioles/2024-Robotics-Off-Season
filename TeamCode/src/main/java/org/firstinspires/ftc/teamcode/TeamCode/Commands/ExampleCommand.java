package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.ExampleSubsystem;

public class ExampleCommand extends CommandBase {
    /*
        Add Subsystems as object
        format: private final ExampleSubsystem m_exampleSubsystem;
     */
    private final ExampleSubsystem m_exampleSubsystem;

    public ExampleCommand(ExampleSubsystem subsystem){
        m_exampleSubsystem = subsystem;
        addRequirements(m_exampleSubsystem);
    }

    @Override
    public void initialize(){
        /*
        Runs once when command called
         */
    }

    @Override
    public void execute(){
        /*
        Runs repeatedly until isFinished() is done
         */
    }
    @Override
    public void end(boolean interrupted){
        /*
        Runs once execute() ends
        change requirements depending on
         end case we are looking for
         */
    }

    @Override
    public boolean isFinished() {
        /*
            Checks if end requirement is true
            runs once after command stops
         */
        return true;
    }


}
