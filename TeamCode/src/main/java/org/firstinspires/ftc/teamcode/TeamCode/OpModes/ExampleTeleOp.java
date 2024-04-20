package org.firstinspires.ftc.teamcode.TeamCode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.FunctionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem14;

@TeleOp
public class ExampleTeleOp extends CommandOpMode {
    private GamepadEx gamepadP;
    private IntakeSubsystem14 intake;
    private ClawSubsystem claw;
    private ArmSubsystem arm;
    @Override
    public void initialize() {
        gamepadP = new GamepadEx(gamepad1);

        intake = new IntakeSubsystem14(hardwareMap, "intakeMotor");
        claw = new ClawSubsystem(hardwareMap, "clawServo");
        arm = new ArmSubsystem(hardwareMap, "servoLiftServo", "endArmServo");

        gamepadP.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(intake::intake, intake))
                .whenReleased(new InstantCommand(intake::stop, intake));
        gamepadP.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(intake::outtake, intake))
                .whenReleased(new InstantCommand(intake::stop, intake));
        gamepadP.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new FunctionalCommand(
                        () ->{},
                        claw::open,
                        (Boolean interrupted) -> {},
                        claw::isFinished,
                        claw
                ));
        gamepadP.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new FunctionalCommand(
                        () ->{},
                        claw::close,
                        (Boolean interrupted) -> {},
                        claw::isFinished,
                        claw
                ));




    }
    @Override
    public void runOpMode() throws InterruptedException{
        this.initialize();
        this.waitForStart();

        while (!this.isStopRequested() && this.opModeIsActive()){
            this.run();
            arm.setFullArm(arm.getCurrentArmPosDeg() + gamepadP.getLeftY() *0.5
            );
        }
        this.reset();

    }

}
