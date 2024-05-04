package org.firstinspires.ftc.teamcode.TeamCode.OpModes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.TeamCode.Commands.Collectpixels;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.GetPixels;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.StopGetPixels;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.liftrun;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.liftstop;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.LiftSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

@TeleOp
public class test_15 extends CommandOpMode {
    private GamepadEx gamepadB;
    private IntakeSubsystem15 Intake15455;
    private PassSubsystem15 pass15455;
    private LiftSubsystem15 liftSubsystem15;
    private RevTouchSensor toucher1 = null;
    private RevTouchSensor toucher2 = null;
    private boolean touched =true;

    @Override
    public void initialize() {
        gamepadB = new GamepadEx(gamepad2);

        Intake15455 = new IntakeSubsystem15(hardwareMap, "intake", "ts1");
        pass15455 = new PassSubsystem15(hardwareMap, "path", "ts2");
        liftSubsystem15 = new LiftSubsystem15(hardwareMap, "lift");
        toucher1 = hardwareMap.get(RevTouchSensor.class, "ts1");
        toucher2 = hardwareMap.get(RevTouchSensor.class, "ts2");

        gamepadB.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new Collectpixels(Intake15455, pass15455))
                .whenReleased(new StopGetPixels(Intake15455,pass15455));
    }
//        @Override
//    public void run() {
////        if (toucher1.isPressed()) {
////            new GetPixels(Intake15455, pass15455).execute();
////        } else {
////            new StopGetPixels(Intake15455, pass15455).execute();
////        }
//
//        if (toucher2.isPressed()) {
//            new liftrun (liftSubsystem15).execute();
//        } else {
//            new liftstop(liftSubsystem15).execute();
//        }
//
//
//
//    }
}
