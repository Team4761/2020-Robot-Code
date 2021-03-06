package org.robockets.infiniterecharge;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

    //drivetrain
    public static CANSparkMax FrontLeft = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax BackLeft = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax FrontRight = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax BackRight = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static CANEncoder FrontLeftEncoder = FrontLeft.getEncoder();
    public static CANEncoder BackLeftEncoder = BackLeft.getEncoder();
    public static CANEncoder FrontRightEncoder = FrontRight.getEncoder();
    public static CANEncoder BackRightEncoder = BackRight.getEncoder();

    public static SpeedControllerGroup Left = new SpeedControllerGroup(FrontLeft, BackLeft);
    public static SpeedControllerGroup Right = new SpeedControllerGroup(FrontRight, BackRight);

    public static DifferentialDrive RobotDrive = new DifferentialDrive(Right,Left);

    //gyro
    public static ADXRS450_Gyro Gyro = new ADXRS450_Gyro();
    public static PIDController gyroPIDController = new PIDController(0.0, 0.0, 0.0); //FIXME: get the values right!

    //wheel
    public static ColorSensorV3 ColorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    public static Spark WheelSpinner = new Spark(0);
    public static CANSparkMax WheelArm = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushed);
    public static PIDController wheelPIDController = new PIDController(0,0,0); //FIXME: get values right!

    //shooter
    public static VictorSPX PolyCordControllerBottom = new VictorSPX(9);
    public static VictorSPX PolyCordControllerTop = new VictorSPX(10);
    public static CANSparkMax FlyWheelTop = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax FlyWheelBottom = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static VictorSPX Intake = new VictorSPX(7);

    public static CANEncoder FlyWheelTopEncoder = FlyWheelTop.getEncoder();
    public static CANEncoder FlyWheelBottomEncoder = FlyWheelBottom.getEncoder();

    public static DigitalInput intakeBreakBeam = new DigitalInput(8); //DIO port
    public static DigitalInput BreakBeamLED = new DigitalInput(7);
    public static DigitalInput flywheelBreakBeam = new DigitalInput(9);

    //climber
    public static VictorSP Telescope = new VictorSP(1); //The PWM cable will be spilt between left and right

    public static CANSparkMax ReelInLeft = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless); //One for each.
    public static CANSparkMax ReelInRight = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static CANEncoder ReelInLeftEncoder = new CANEncoder(ReelInLeft);
    public static CANEncoder ReelInRightEncoder = new CANEncoder(ReelInRight);

    //Vision crap (if we actually do this...)
    public static DigitalInput laser = new DigitalInput(3);

    //LED
    public static AddressableLED led = new AddressableLED(9);
    public static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(30);
}
