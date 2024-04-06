package org.firstinspires.ftc.teamcode.FtcLibComp_Running_Code.FtcLibCall_Upon_Classes;

import android.graphics.Canvas;
import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.CameraControl;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class VisionLib extends SubsystemBase {

    private  AprilTagProcessor processor;
    private  VisionPortal vportal;
    private final Telemetry telemetry;



    public VisionLib(HardwareMap hardwareMap, String webcamName, Telemetry telemetry) {
        this.telemetry = telemetry;
        processor = new AprilTagProcessor.Builder()
//                .setTagLibrary(AprilTagProcessor.TagFamily.TAG_36h11)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .setLensIntrinsics(822.317f, 822.317f, 319.495f, 242.502f)
                .setDrawCubeProjection(true)
                .build();

        vportal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, webcamName))
                .addProcessor(processor)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
//               .enableCameraMonitoring(true)
                .setAutoStopLiveView(true)
                .build();
    }

    public double[] doIt(){
        ArrayList<AprilTagDetection> detections = processor.getDetections();
        double [ ] coords = new double[] {0,0,0,0,0,0,0};
        double startZcoord = 0;
        double startZRot =   0;
        double startRange =  0;
        double startBearing =0;
        try {
            startZcoord = detections.get(0).ftcPose.z;
            startZRot = detections.get(0).ftcPose.yaw;
            startRange = detections.get(0).ftcPose.range;
            startBearing = detections.get(0).ftcPose.bearing;
        } catch (Exception e){
            startZcoord = 0;
            startZRot =   0;
            startRange =  0;
            startBearing =0;
        }
        int numDetections = 1;
//        for (AprilTagDetection detection : detections) {
//            if (detection != null) {
//                int id = detection.id;
//                startZcoord    += detection.ftcPose.z;
//                startZRot      += detection.ftcPose.yaw;
//                startRange     += detection.ftcPose.range;
//                startBearing   += detection.ftcPose.bearing;
//
//                numDetections++;
//
//            }
//        }
//        if (numDetections !=0){
//            coords = new double[]{
//                    startRange  /numDetections,
//                    startBearing/numDetections,
//                    startZcoord /numDetections,
//                    startZRot   /numDetections,
//            };
//        }
        coords = new double[]{
                    startRange  /numDetections,
                    startBearing/numDetections,
                    startZcoord /numDetections,
                    startZRot   /numDetections,
            };
        return coords;
    }

}
