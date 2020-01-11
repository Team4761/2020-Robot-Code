package org.robockets.infiniterecharge;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

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

    public static DifferentialDrive robotDrive = new DifferentialDrive(Left,Right);

    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();


}
