package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code;

import com.arcrobotics.ftclib.command.CommandScheduler;


import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.DriveCommands;

import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.DroneLaunchSubsystem;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.HangCommand;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.HangSubsystem;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.GrabCommands;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.HandOpen;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.IntakeRotLib;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.Intake_Lib;


import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.HandRotSubsystem;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.LiftServoSubsystem;
import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.LinearLiftSubsystem;

import org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes.LiftArmHand.ScoreCommands;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "FTC Lib Test")
public class FTCLibCompTeleOp extends OpMode {



    private  GamepadEx drivePad = null;
    private GamepadEx gamepadEx2 =null;



    private double startIMU = 0;
    private  IMU imu;
    private DriveCommands drive;

    //Arm Stuff
    private HandOpen grabber ;
    private LiftServoSubsystem liftServo = null;
    private LinearLiftSubsystem linearLift = null;
    private HandRotSubsystem handRot =null;

    private Button gamePad2SequenceGrabButton;
    private GrabCommands grabCommand = null;
    private ScoreCommands scoreCommand = null;

    //EndGame
    private DroneLaunchSubsystem droneLauncher;
    private HangSubsystem hangSubsystem;

    private HangCommand hangCommand;


    private String driveCommandError = "";
    private String scoreCommandError = "";
    private final Consumer<String> errorAdder = (String error) ->{
        if (!error.equals("")){
            telemetry.addData("Error",error);
        }
    };


    @Override
    public void init() {


        drivePad = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        final ToggleButtonReader gamePad2ToggleX;
        gamePad2ToggleX = new ToggleButtonReader(gamepadEx2, GamepadKeys.Button.X);
        gamePad2SequenceGrabButton = new GamepadButton(gamepadEx2, GamepadKeys.Button.DPAD_LEFT);

        Button droneButton1 = new GamepadButton(gamepadEx2, GamepadKeys.Button.A);
        Button droneButton2 = new GamepadButton(gamepadEx2,GamepadKeys.Button.B);
        Button hangButtonX = new GamepadButton(gamepadEx2,GamepadKeys.Button.X);
        Button hangButtonY = new GamepadButton(gamepadEx2,GamepadKeys.Button.Y);




        //ToDo: Comment these lines out when doing the first test. If that works, uncomment these and see if they work



        //=============================================
        //Arm Stuff
        //=============================================
        grabber = new HandOpen(hardwareMap, gamePad2ToggleX::getState);
        liftServo = new LiftServoSubsystem(hardwareMap, gamepadEx2::getLeftY,telemetry);

        linearLift = new LinearLiftSubsystem(hardwareMap,gamepadEx2::getRightY,telemetry);
        handRot = new HandRotSubsystem(hardwareMap,gamepadEx2::getLeftX,telemetry);


        grabCommand = new GrabCommands(handRot,liftServo,linearLift,grabber,telemetry);
        scoreCommand = new ScoreCommands(grabber,liftServo,linearLift,handRot,telemetry);


        //=============================================
        //Intake Stuff
        //=============================================
        IntakeRotLib intakeRot = new IntakeRotLib(
                hardwareMap,
                () -> drivePad.getButton(GamepadKeys.Button.DPAD_UP),
                () -> drivePad.getButton(GamepadKeys.Button.DPAD_DOWN)
                );

        Intake_Lib intake = new Intake_Lib(
                hardwareMap,
                () -> drivePad.getButton(GamepadKeys.Button.A),
                () -> drivePad.getButton(GamepadKeys.Button.B)
        );
        //=============================================
        //IMU/Drive
        //=============================================
        imu = hardwareMap.get(IMU.class,"imu");
        IMU.Parameters imuParams = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.initialize(imuParams);
        drive = new DriveCommands(hardwareMap,
                drivePad::getLeftY,
                drivePad::getLeftX,
                drivePad::getRightX,
                () -> AngleUnit.normalizeDegrees(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES))
        );


        //=============================================
        //Drone Launcher/Hang
        //=============================================

        droneLauncher = new DroneLaunchSubsystem(hardwareMap);
        hangSubsystem = new HangSubsystem(hardwareMap);
        hangCommand = new HangCommand(hangSubsystem,liftServo,linearLift);
        Trigger doHangTrigger = new Trigger(hangButtonX::get).and(hangButtonY).whenActive(hangCommand.interruptOn(
                new Trigger(hangButtonY.negate()::get).and(hangButtonX)::get
        ));
        gamePad2SequenceGrabButton.whenPressed(grabCommand);
        Trigger droneTrigger = new Trigger(droneButton1::get).and(droneButton2).whenActive(new InstantCommand(droneLauncher::launch));

    }


    @Override
    public void loop(){
        drivePad.readButtons();
        gamepadEx2.readButtons();
        try {
            drive.schedule();
        } catch (Exception e){
            driveCommandError = "Drive Command Error: "+e.getMessage();
        }

        scoreCommand.schedule();



        errorAdder.accept(driveCommandError);


        //CommandScheduler.getInstance().setDefaultCommand(hangSubsystem,hangCommand);
        CommandScheduler.getInstance().run();







        telemetry.addLine("Intake Rotate Up: up/down dpad gamepad 1");
        telemetry.addLine("Intake In: button a gamepad1");
        telemetry.addLine("Intake Out: button b gamepad1");
        telemetry.addLine();
        telemetry.addLine("Arm Up: left joystick gamepad 2");
        telemetry.addLine("Rotate Hand: left joystick gamepad 2");
        telemetry.addLine("Grab: toggle x gamepad2");
        telemetry.addLine("Linear Lift: right joystick gamepad 2");
        telemetry.addLine("Grab Command: left dpad gamepad2");
        telemetry.addLine();
        telemetry.addLine("Drone: a and b gamepad 2");
        telemetry.addLine("Hang Command: x and y gamepad2");
        telemetry.addLine("CANCEL Hang Command: only x gamepad2");




        telemetry.update();
    }
}
