package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import vision.Webcam;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous
public class DragRaceAuto extends OpMode {
    //@Override
// This below here is the code for the actual claw
// driveOmni(-1*gamepad1.left_stick_y, 1*gamepad1.right_stick_x, 1*gamepad1.left_stick_x);
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;
    // down there makes a webcam
//    private Webcam webcam;

    int step=0;
    int delayStep=-1;
    double endTime;
    public ElapsedTime timer = new ElapsedTime();
    NormalizedColorSensor colorSensor;

    static final double WHITE_THRESHOLD = 0.5;  // spans between 0.0 - 1.0 from dark to light
    static final double APPROACH_SPEED  = 0.25;
    

    public void delayedStop(double delay){
        if (delayStep!=step){
            delayStep=step;
            endTime=timer.milliseconds()+delay;

        }
        if (timer.milliseconds()>=endTime) {
            stopRobot();
            step++;
        }
        //stops
    }

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
    }
    
    public void driveOmni(double y, double rx, double x) {
        double maxValue = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double flPower = (y + x + rx) / maxValue;
        double blPower = (y - x + rx) / maxValue;
        double frPower = (y - x - rx) / maxValue;
        double brPower = (y + x - rx) / maxValue;

        frontLeft.setPower(-flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(-blPower);
        backRight.setPower(brPower);

    }

    public void stopRobot(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    @Override
    public void loop() {
//        driveOmni(1, 00, 0);
/*        if () {
            stopRobot();
        }
 */

    }
}
