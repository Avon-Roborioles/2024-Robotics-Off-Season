package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand;

import androidx.annotation.Nullable;

import com.arcrobotics.ftclib.command.SubsystemBase;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class LinearLiftSubsystem extends SubsystemBase {


    private final Motor linearLiftMotor;
    private int targetPos;

    private final DoubleSupplier control;
    private final int speed =10; //Linear lift Speed
    private final int liftGrabPos = 0;
    private final Telemetry telemetry;

    public LinearLiftSubsystem(HardwareMap hardwareMap, DoubleSupplier control, Telemetry telemetry) {
        linearLiftMotor = new Motor(hardwareMap,"linearLiftMotor");
        this.control = control;
        linearLiftMotor.setTargetPosition(0);
        linearLiftMotor.setPositionCoefficient(.05);
        linearLiftMotor.setPositionTolerance(10);
        linearLiftMotor.setRunMode(Motor.RunMode.PositionControl);
        this.telemetry = telemetry;
        linearLiftMotor.set(1);

    }

    public void setToGrab(){
        //Linear lift motor set positions
        linearLiftMotor.setTargetPosition(liftGrabPos);

    }

    public double getLiftPos(){return linearLiftMotor.getCurrentPosition();}


    public void setToScore(){

        //minus because of the inverted controls
        targetPos -= (int)(speed* control.getAsDouble());
        linearLiftMotor.setTargetPosition(targetPos);
        telemetry.addData("Linear Lift Target Pos", targetPos);
        telemetry.addData("Linear Lift Actual Pos",linearLiftMotor.getCurrentPosition());
    }
    public void setToGetOut() {

        linearLiftMotor.setTargetPosition(300);
    }
    public void setToHang(){
        targetPos = 500;
        linearLiftMotor.setTargetPosition(targetPos);
    }
    public boolean isAtTarget(){
        return linearLiftMotor.getCurrentPosition()==targetPos;
    }


}
