package org.robockets.infiniterecharge;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

    //drivetrain
    public static CANSparkMax FrontLeft = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax BackLeft = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax FrontRight = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax BackRight = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static CANEncoder FrontLeftEncoder = FrontLeft.getEncoder();
    public static CANEncoder BackLeftEncoder = BackLeft.getEncoder();
    public static CANEncoder FrontRightEncoder = FrontRight.getEncoder();
    public static CANEncoder BackRightEncoder = BackRight.getEncoder();

    public static SpeedControllerGroup Left = new SpeedControllerGroup(FrontLeft, BackLeft);
    public static SpeedControllerGroup Right = new SpeedControllerGroup(FrontRight, BackRight);

    public static DifferentialDrive RobotDrive = new DifferentialDrive(Left,Right);

    //gyro
    public static ADXRS450_Gyro Gyro = new ADXRS450_Gyro();

    //wheel //TODO: find actual ports!!!
    public static ColorSensorV3 ColorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    public static Spark WheelSpinner = new Spark(4);
    public static Encoder WheelSpinnerEncoder = new Encoder(4,1); //TODO: find correct ports
    public static Solenoid PistonArm = new Solenoid(5);

    //shooter


    //climber
    public static Spark TelescopeLeft = new Spark(6);
    public static Spark TelescopeRight = new Spark(7);

    public static Spark ReelInLeft = new Spark(8);
    public static Spark ReelInRight = new Spark(9);


}
