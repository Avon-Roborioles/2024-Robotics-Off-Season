package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TeamCode.AprilTagReader;


public abstract class AprilTagLocalizer implements Localizer{
    private AprilTagReader aprilTag;
    public static ThreeDeadWheelLocalizer.Params PARAMS = new ThreeDeadWheelLocalizer.Params();

    public final Encoder par0, par1, perp;

    public final double inPerTick;

    private int lastPar0Pos, lastPar1Pos, lastPerpPos;
    private Vector2dDual<Time> vecError;

    private double headingError;


    private Twist2dDual<Time> error = new Twist2dDual<>(
            new Vector2dDual<>(
                    new DualNum<Time>(new double[]{0,0}),
                    new DualNum<Time>(new double[]{0,0})
                    ),
            new DualNum<Time>(
                    new double[]{0,0}
            )
    );

    //In aprilTag coords



    public class Params {
        public double par0YTicks = -1665.1975338839725; // y position of the first parallel encoder (in tick units)
        public double par1YTicks = 1639.2334322674708; // y position of the second parallel encoder (in tick units)
        public double perpXTicks = 1146.925049950169;//1076.9986; // x position of the perpendicular encoder (in tick units)
    }
    public AprilTagLocalizer(HardwareMap hardwareMap, double inPerTick, AprilTagReader aprilTagSensor){
        perp = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "par1")));
        par1 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "frontLeft")));
        par0 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "backRight/perp")));
        par0.setDirection(DcMotorSimple.Direction.REVERSE);
        lastPar0Pos = par0.getPositionAndVelocity().position;
        lastPar1Pos = par1.getPositionAndVelocity().position;
        lastPerpPos = perp.getPositionAndVelocity().position;
        this.inPerTick = inPerTick;

        this.aprilTag = aprilTagSensor;
    }

    @Override
    public final Twist2dDual<Time> update(){
        PositionVelocityPair par0PosVel = par0.getPositionAndVelocity();
        PositionVelocityPair par1PosVel = par1.getPositionAndVelocity();
        PositionVelocityPair perpPosVel = perp.getPositionAndVelocity();

        Twist2dDual<Time> reading = subUpdate();

        int par0PosDelta = par0PosVel.position - lastPar0Pos;
        int par1PosDelta = par1PosVel.position - lastPar1Pos;
        int perpPosDelta = perpPosVel.position - lastPerpPos;

        Twist2dDual<Time> twist = new Twist2dDual<>(
                reading.line.plus(vecError),
                new DualNum<Time>(new double[] {
                        reading.angle.value() + headingError,
                        reading.angle.values().get(1),
                })
        );

        lastPar0Pos = par0PosVel.position;
        lastPar1Pos = par1PosVel.position;
        lastPerpPos = perpPosVel.position;

        return twist;
    }

    public abstract Twist2dDual<Time> subUpdate();

    public void readAprilTag(){
        Twist2dDual<Time> reading=aprilTag.readTag();
        Twist2dDual<Time> curPos=update();
        vecError = new Vector2dDual<>(reading.line.y.times(1/inPerTick),reading.line.x.times(1/inPerTick)).minus(curPos.line);
        headingError = Math.toDegrees(reading.angle.value())-curPos.angle.value();

    }


    public double inToTick(double inch){
        return inch*1/inPerTick;
    }
}
