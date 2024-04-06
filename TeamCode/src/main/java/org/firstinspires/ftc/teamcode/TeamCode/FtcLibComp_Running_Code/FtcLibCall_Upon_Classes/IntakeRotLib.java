package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class IntakeRotLib extends SubsystemBase {

    private final Motor motor;

    private final BooleanSupplier upControl;
    private final BooleanSupplier downControl;
    private  int maxPos;
    private int minPos;



    public IntakeRotLib(HardwareMap hardwareMap, BooleanSupplier upControl, BooleanSupplier downControl){

        motor = new Motor(hardwareMap, "intakeLift");
        motor.setRunMode(Motor.RunMode.PositionControl);

        this.upControl =upControl;
        this.downControl = downControl;

    }
    @Override
    public void periodic(){
        int rawControl =0;
        if (upControl.getAsBoolean()){
            rawControl++;
        } else if (downControl.getAsBoolean()) {
            rawControl--;
        }

        motor.setTargetPosition(Range.clip(motor.getCurrentPosition()+(10*rawControl),minPos,maxPos));
    }



}
