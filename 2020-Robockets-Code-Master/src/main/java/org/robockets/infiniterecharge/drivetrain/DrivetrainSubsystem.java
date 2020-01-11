package org.robockets.infiniterecharge.drivetrain;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.RobotMap;

import static org.robockets.infiniterecharge.OI.*;

public class DrivetrainSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    private final double INCHES_PER_TICK = 1.0; // TODO: Get an actual value

    private PIDController leftPodPIDController;
    private PIDController rightPodPIDController;

    //private GyroPIDOutput gyroPIDOutput;
    private PIDController gyroPIDController;

    //DoubleEncoderPIDSource leftPIDSource;
    //DoubleEncoderPIDSource rightPIDSource;

    public static final double TRANSLATE_SPEED = 0.85;
    public static final double ROTATE_SPEED = 0.85;

    /**
     * The Singleton instance of this DrivetrainSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static DrivetrainSubsystem INSTANCE = new DrivetrainSubsystem();

    /**
     * Creates a new instance of this DrivetrainSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private DrivetrainSubsystem() {

    }

    /**
     * Returns the Singleton instance of this DrivetrainSubsystem. This static method
     * should be used -- {@code DrivetrainSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static DrivetrainSubsystem getInstance() {
        return INSTANCE;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       e.g. setDefaultCommand(new MyCommand());
    }

    public void driveArcade(double translate, double rotate) {
        if(usingxbox)
            RobotMap.robotDrive.arcadeDrive(translate,rotate);
        //This can and will change dependent on what controller we use
    }

    public void stop() {
        RobotMap.robotDrive.arcadeDrive(0.0, 0.0);
    }
}

