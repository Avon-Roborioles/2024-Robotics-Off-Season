package org.firstinspires.ftc.teamcode.TeamCode.OpModes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeamCode.Commands.Intake15;
import org.firstinspires.ftc.teamcode.TeamCode.Commands.Pass15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.IntakeSubsystem15;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.PassSubsystem15;

@TeleOp
public class test_15 extends CommandOpMode {
    private GamepadEx gamepadB;
    private IntakeSubsystem15 Intake15455;
    private PassSubsystem15 pass15455;
    private Intake15 cmdintake15;
    private Pass15 cmdpass15;

    @Override
    public void initialize() {
        gamepadB = new GamepadEx(gamepad2);
        Intake15455 = new IntakeSubsystem15(hardwareMap, "intake");
        pass15455 = new PassSubsystem15(hardwareMap, "path");


        gamepadB.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new ParallelCommandGroup(new Intake15(Intake15455::intake15run), new Pass15(pass15455::Pass15)))
                .whenReleased(new ParallelCommandGroup(new Intake15(Intake15455::stop15), new Pass15(pass15455::stoppass)));

    }


}
