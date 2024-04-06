package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;


import com.arcrobotics.ftclib.command.FunctionalCommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.LiftServoSubsystem;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.LinearLiftSubsystem;

public class HangCommand extends SequentialCommandGroup {


    private final HangSubsystem hang;
    public HangCommand(HangSubsystem hang, LiftServoSubsystem arm, LinearLiftSubsystem lift){
        this.hang = hang;
        addCommands(
                new FunctionalCommand(
                        () ->{},
                        arm::setToHang,
                        (Boolean filler) ->{},
                        () -> true,
                        arm
                ),

                new FunctionalCommand(
                () ->{},
                hang::hang,
                (Boolean filler) ->{},
                hang::isAtHangPos,
                hang
                ),
                new FunctionalCommand(
                        () ->{},
                        lift::setToHang,
                        (Boolean filler) ->{},
                        lift::isAtTarget,
                        lift
                )
        );
    }






    @Override
    public void end(boolean interrupted){
        if (interrupted){
            hang.cancel();
        }
    }
}
