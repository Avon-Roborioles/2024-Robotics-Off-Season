package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.ui.ThemedActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VisionCommands extends CommandBase {

    private VisionLib vision = null;
    private ArrayList<double[]> coords = new ArrayList<>(0);
    private Telemetry telemetry = null;


    public VisionCommands(VisionLib vision, Telemetry telemetry){
        this.vision = vision;
        this.telemetry = telemetry;
    }
    @Override
    public void initialize(){
        ArrayList<double[]> coords = new ArrayList<>(0);
        telemetry.addData("In the init",0);
    }
    @Override
    public void execute(){
        telemetry.addData("In the execute (getting data)",0);
        coords.add(vision.doIt());

    }
    @Override
    public boolean isFinished(){
        int x=0;

        while (x<10000 ){
            x++;
        }
        telemetry.addData("Finished the is finished",0);
        return true;
    }
    @Override
    public void end(boolean interrupted){

        double bearing = 0;
        double range = 0;
        double zcoord = 0;
        double botx = 0;
        double boty = 0;
        double heading = 0;
        double zRot = 0;
        telemetry.addData("In the end (calculate)",0);

        int numItems = 0;

        for (double[] i: coords){

            range   += i[0];
            bearing += i[1];
            zRot    += i[2];
            zcoord  += i[3];


            numItems++;
        }


        range      /=numItems;
        bearing    /=numItems;
        zcoord     /=numItems;
        zRot       /=numItems;



        double realRange = Math.sqrt(Math.pow(range,2)-Math.pow(zcoord,2));
        double pb = bearing - zRot ;






        botx = Math.sin(pb)*realRange;
        boty = Math.cos(pb)*realRange;

        heading = 270-bearing;
        double precision = 2;
        double adjust = Math.pow(10,precision);
        telemetry.addData("======================", " ");

//        telemetry.addData("Tag ID: ", id);
        telemetry.addData("X Distance From April Tag", Math.round(botx  * adjust) / adjust);
        telemetry.addData("Y Distance From April Tag", Math.round(boty  * adjust) / adjust);
        telemetry.addData("Range", range);
        //telemetry.addData("Z Distance From April Tag: ", Math.round(botz*precision)/precision);


        telemetry.addData("Yaw", zRot);
        telemetry.addData("Bearing", bearing);
        telemetry.addData("Parallel Bearing", pb);
//        telemetry.addData("Update Status", version);
    }
}
