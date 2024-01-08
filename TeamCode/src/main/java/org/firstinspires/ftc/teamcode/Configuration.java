package org.firstinspires.ftc.teamcode;

public class Configuration {

    public static final String CAMERA = "Webcam 1";

    public class LIFT {
        public static final String LEFT_LIFT = "lift1";
        public static final String RIGHT_LIFT = "lift2";
    }

    public class ClawConfiguration {
        public static final String LEFT = "leftClaw";
        public static final String RIGHT = "rightClaw";
    }

    public class TractionConfiguration {
        public static final String FRONT_LEFT = "frontLeft";
        public static final String FRONT_RIGHT = "frontRight";
        public static final String BACK_LEFT = "backLeft";
        public static final String BACK_RIGHT = "backRight";

        public static final String LEFT_ENCODER = "leftEncoder";
        public static final String RIGHT_ENCODER = "rightEncoder";
        public static final String FRONT_ENCODER = BACK_LEFT;

    }
    public class ArmConfiguration{
        public static final String LEFT="leftArm";
        public static final String RIGHT="rightArm";
    }

    public class SensorConfiguration {
        public static final String SENSOR_IR_LEFT = "sensorIRLeft";
        public static final String SENSOR_IR_RIGHT = "sensorIRRight";
        public static final String SENSOR_DISTNACE = "distance";
    }

    public class AirplaneLauncherConfiguration{
        public static final String LAUNCHER="launcherServo";
    }
}
