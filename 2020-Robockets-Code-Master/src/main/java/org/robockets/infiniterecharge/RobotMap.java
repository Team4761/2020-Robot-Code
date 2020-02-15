package org.robockets.infiniterecharge;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.*;
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

    //Just testing to see if the motors are configured correctly
    public static DifferentialDrive RobotDrive = new DifferentialDrive(Right,Left);

    //gyro
    public static ADXRS450_Gyro Gyro = new ADXRS450_Gyro();

    //wheel //TODO: find actual ports!!!
    public static ColorSensorV3 ColorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    public static Spark WheelSpinner = new Spark(0);
    public static TalonSRX WheelArm = new TalonSRX(7);

    //shooter
    public static VictorSPX PolyCordControllerTop = new VictorSPX(9);
    public static VictorSPX PolyCordControllerBotton = new VictorSPX(10);
    //public static CANSparkMax FlyWheel1 = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    //public static CANSparkMax FlyWheel2 = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static VictorSPX InputWheel = new VictorSPX(8);

    public static DigitalInput intakeBreakBeam = new DigitalInput(0); //DIO port
    public static DigitalInput intakeLED = new DigitalInput(1);
    public static DigitalInput flywheelBreakBeam = new DigitalInput(2);

    //climber
    public static VictorSPX Telescope = new VictorSPX(6); //The PWM cable will be spilt between left and right

    public static CANSparkMax ReelInLeft = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless); //One for each.
    public static CANSparkMax ReelInRight = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);

    //Vision crap (if we actually do this...)
    public static DigitalInput laser = new DigitalInput(3);

    //LED
    public static AddressableLED led = new AddressableLED(9);
    public static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(30);

}
