package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

public class DriveCommands extends CommandBase {

    private final MecanumDrive drive;
    private DoubleSupplier headingSupplier;
    private final DoubleSupplier forward;
    private final DoubleSupplier turnSupplier;
    private final DoubleSupplier strafe;

    public DriveCommands(HardwareMap hardwareMap, DoubleSupplier forwardSupplier, DoubleSupplier strafe, DoubleSupplier turn, DoubleSupplier heading){
        this.forward = forwardSupplier;
        this.strafe =strafe;
        this.turnSupplier = turn;
        this.headingSupplier = heading;
        drive = new com.arcrobotics.ftclib.drivebase.MecanumDrive(
                new Motor(hardwareMap, "frontLeft"),
                new Motor(hardwareMap, "frontRight"),
                new Motor(hardwareMap,"backLeft"),
                new Motor(hardwareMap,"backRight/perp")
        );
    }


    public void setHeadingSupplier(DoubleSupplier headingSupplier){
        this.headingSupplier = headingSupplier;
    }
    @Override
    public void execute() {



        drive.driveFieldCentric(

                -10*forward.getAsDouble(),
                -10*strafe.getAsDouble(),
                -10*turnSupplier.getAsDouble(),
                headingSupplier.getAsDouble()
        );
    }
}


