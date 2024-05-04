package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem15 extends SubsystemBase {

    private final DcMotor intake;
    private final double intakeSpeed = 1;
    private RevTouchSensor toucher;
    private RevTouchSensor toucher2;
    public IntakeSubsystem15(final HardwareMap hardwareMap, final String name, final String name1){
        intake = hardwareMap.get(DcMotor.class, name);
        toucher = hardwareMap.get(RevTouchSensor.class, name1);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
       public boolean touchsensor() {
           if (toucher.isPressed()) {
               return true;
           } else {
               return false;
           }
       }

        public void intake15run(){
            intake.setPower(-intakeSpeed);
        }



        public void outtake15(){
            intake.setPower(intakeSpeed);
        }

        public void stop15(){
            intake.setPower(0);
        }
    }


