package org.firstinspires.ftc.teamcode.TeamCode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem14;

@TeleOp
public class ExampleTeleOp extends CommandOpMode {
    private GamepadEx gamepadP;
    private IntakeSubsystem14 intake;

    @Override
    public void initialize() {
        gamepadP = new GamepadEx(gamepad1);

        intake = new IntakeSubsystem14(hardwareMap, "intakeMotor");

        gamepadP.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(intake::intake, intake))
                .whenReleased(new InstantCommand(intake::stop, intake));
        gamepadP.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(intake::outtake, intake))
                .whenReleased(new InstantCommand(intake::stop, intake));


    }

}
