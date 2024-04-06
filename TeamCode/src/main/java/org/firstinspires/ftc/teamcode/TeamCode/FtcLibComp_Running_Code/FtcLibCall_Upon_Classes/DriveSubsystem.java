package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

public class DriveSubsystem extends SubsystemBase {
    private MecanumDrive drive = null;
    private DoubleSupplier forward;
    private DoubleSupplier strafe;
    private DoubleSupplier turn;
    private DoubleSupplier heading;
    private String runMode;


    /**
     *
     * @param hardwareMap
     * @param runMode robot means robot-centric. defaults to field centric
     * @param forward
     * @param strafe
     * @param turn
     * @param heading
     */
    public DriveSubsystem(HardwareMap hardwareMap,String runMode, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn, DoubleSupplier heading){
        drive = new com.arcrobotics.ftclib.drivebase.MecanumDrive(
                new Motor(hardwareMap,"frontLeft"),
                new Motor(hardwareMap,"frontRight"),
                new Motor(hardwareMap, "backLeft"),
                new Motor(hardwareMap, "backRight/perp")
        );
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.heading = heading;
        this.runMode = runMode;
        if (heading == null){
            runMode = "robot";
        }
    }



    public void drive(){
        if(runMode.equals("robot")) {
            drive.driveRobotCentric(
                    -strafe.getAsDouble(),
                    -forward.getAsDouble(),
                    -turn.getAsDouble()
            );
        }else{
            drive.driveFieldCentric(
                    -strafe.getAsDouble(),
                    -forward.getAsDouble(),
                    -turn.getAsDouble(),
                    heading.getAsDouble()
            );
        }
    }

    @Override
    public void periodic(){
        drive();
    }
}
