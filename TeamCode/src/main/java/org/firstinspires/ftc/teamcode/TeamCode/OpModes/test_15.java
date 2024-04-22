package org.firstinspires.ftc.teamcode.TeamCode.OpModes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.TeamCode.Commands.GetPixels;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.StopGetPixels;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

@TeleOp
public class test_15 extends CommandOpMode {
    private GamepadEx gamepadB;
    private IntakeSubsystem15 Intake15455;
    private PassSubsystem15 pass15455;
    private RevTouchSensor toucher1 = null;
    private boolean touched = false;



//    public void runTouchBackboard() {
//        if (toucher1.isPressed()) {
//            touched = true;
//        } else {
//            touched = false;
//        }
//    }
        @Override
    public void initialize() {
        gamepadB = new GamepadEx(gamepad2);
        Intake15455 = new IntakeSubsystem15(hardwareMap, "intake");
        pass15455 = new PassSubsystem15(hardwareMap, "path");
        toucher1 = hardwareMap.get(RevTouchSensor.class,"ts1");


       gamepadB.getGamepadButton(GamepadKeys.Button.A)

                .whenPressed(new GetPixels(Intake15455, pass15455))
                .whenReleased(new StopGetPixels(Intake15455,pass15455));


    }
}
