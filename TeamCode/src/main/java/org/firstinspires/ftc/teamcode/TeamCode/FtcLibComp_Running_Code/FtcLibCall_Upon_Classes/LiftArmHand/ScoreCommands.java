package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;


import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScoreCommands extends ParallelCommandGroup {

    public ScoreCommands(
            HandOpen handOpen,

            LiftServoSubsystem liftServo,
            LinearLiftSubsystem linearLift,
            HandRotSubsystem handRot,
            Telemetry telemetry){
        addCommands(
                new InstantCommand(()->{telemetry.addLine("In Score Commands");}),
                new FunctionalCommand(
                        () ->{},
                        handOpen::driverOperate,
                        (Boolean hi) ->{},
                        handOpen::isFinished,
                        handOpen
                ),
                new FunctionalCommand(
                        ()->{},
                        linearLift::setToScore,
                        (Boolean hi)->{},
                        linearLift::isAtTarget,
                        linearLift
                ),
                new FunctionalCommand(
                        () -> {},
                        liftServo::operate,
                        (Boolean hi) ->{},
                        liftServo::isAtOperatePos,
                        liftServo
                ),
                new FunctionalCommand(
                        ()->{},
                        handRot::operate,
                        (Boolean hi) -> {},
                        handRot::isAtTarget,
                        handRot
                )
        );
    }
}
