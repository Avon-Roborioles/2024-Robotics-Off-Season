package org.firstinspires.ftc.teamcode.TeamCode.OpModes;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class touch_sensor {

        private static RevTouchSensor toucher1 = null;
        private RevTouchSensor toucher2 = null;
        private RevTouchSensor toucher3 = null;
        //private Touch
        private static boolean touched = false;
        private boolean touch1 = false;
        public static void initTouch(HardwareMap hardwareMap) {
                toucher1 = hardwareMap.get(RevTouchSensor.class,"ts1");


        }

        public static void runTouchBackboard() {
                if (toucher1.isPressed()) {
                        touched = true;
                } else {
                        touched = false;
                }
        }


        public static void getTelemetry(Telemetry telemetry) {
                telemetry.addData("Is Pressed: ", touched);
        }
}