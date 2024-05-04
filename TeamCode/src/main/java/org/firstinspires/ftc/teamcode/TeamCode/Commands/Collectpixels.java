package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

public class Collectpixels extends ParallelCommandGroup {
    public Collectpixels (IntakeSubsystem15 intake, PassSubsystem15 pass) {
        addCommands(
                new GetPixels(intake),
                new Pass15(pass)
        );
        addRequirements(intake,pass);
    }
}
