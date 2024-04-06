package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.BooleanSupplier;

public class Intake_Lib extends SubsystemBase {
    private Motor intake = null;
    private double power = 0;
    private BooleanSupplier intakeButton;
    private BooleanSupplier buttonB;

    public Intake_Lib(HardwareMap hardwareMap, BooleanSupplier intakeButton, BooleanSupplier buttonB){
        intake = new Motor(hardwareMap,"intake/par0");
        intake.setRunMode(Motor.RunMode.RawPower);
        this.intakeButton =intakeButton;
        this.buttonB = buttonB;


    }




    @Override
    public void periodic(){
        if (intakeButton.getAsBoolean()){
            power = 1;
        } else if ( buttonB.getAsBoolean()) {
            power = -1;
        } else {
            power =0;
        }

        intake.set(power);
    }


    public double getPower(){
        return  power;
    }

//    public class RunIntake implements Action {
//        @Override
//        public boolean run(TelemetryPacket telemetryPacket){
//
//            intake.set(pos);
//            return  intake.get() == pos;
//        }
//    }
//
//    public Action runIntake(boolean input, boolean input2){
//        if (input){
//            pos = 1;
//        } else if (input2) {
//            pos =-1;
//        } else {
//            pos = 0;
//        }
//        return  new RunIntake();
//    }
}
