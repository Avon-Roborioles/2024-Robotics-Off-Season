package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;

import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ProxyScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class GrabCommands extends SequentialCommandGroup {



    public GrabCommands(HandRotSubsystem handRot,
                        LiftServoSubsystem liftServo,
                        LinearLiftSubsystem lift,
                        HandOpen grabber,
                        Telemetry telemetry){


        addCommands(

                new InstantCommand(()->{telemetry.addLine("In Grab Commands");}),
                new ParallelCommandGroup(
                        new FunctionalCommand(
                                ()->{},
                                grabber::open,
                                (Boolean hi)  ->{},
                                grabber::isOpen,
                                grabber
                        ),
                        new FunctionalCommand(
                                () ->{},
                                handRot::grab,
                                (Boolean hi) ->{},
                                handRot::isAtZero,
                                handRot
                        )
                ),
                new FunctionalCommand(
                        liftServo::buttonedGrab,
                        ()->{},
                        (Boolean hi) -> {},
                        liftServo::isGrabFinished,
                        liftServo
                ),
                new FunctionalCommand(
                        () ->{},
                        lift::setToGrab,
                        (Boolean hi) ->{},
                        () ->  lift.getLiftPos() == 0,
                        lift
                ),
                new ProxyScheduleCommand(
                        new FunctionalCommand(
                                grabber::grab,
                                grabber::grab,
                                (Boolean hi)  ->{},
                                grabber::isClosed,
                                grabber
                        )
                ),
                new WaitCommand(500),
                new ProxyScheduleCommand(
                        new FunctionalCommand(
                                () ->{},
                                lift::setToGetOut,
                                (Boolean hi) ->{},
                                () -> lift.getLiftPos() ==300,
                                lift
                        )
                )

        );
    }
}
