package org.robockets.infiniterecharge.drivetrain;


import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import org.robockets.infiniterecharge.RobotMap;

import static org.robockets.infiniterecharge.OI.*;

public class DrivetrainSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    private final double INCHES_PER_TICK = 1.0; // TODO: Get an actual value

    private CANPIDController m_frontleftPodPIDController;
    private CANPIDController m_backleftPodPIDController;

    private CANPIDController m_frontrightPodPIDController;
    private CANPIDController m_backrightPodPIDController;

    //private GyroPIDOutput gyroPIDOutput;
    private PIDController gyroPIDController;

    public static final double TRANSLATE_SPEED = 0.85;
    public static final double ROTATE_SPEED = 0.85;

    private final double Kp = 0.0;
    private final double Ki = 0.0;
    private final double Kd = 0.0;
    private final double max = 1.0;
    private final double min = -1.0;
    private final double KIz = 0;
    private final double KFF = 0;

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
    private DrivetrainSubsystem() { //DoubleEncoder class is TOO hard. So This is the next best option
        m_frontleftPodPIDController = RobotMap.FrontLeft.getPIDController();
        m_backleftPodPIDController = RobotMap.BackLeft.getPIDController();

        m_frontrightPodPIDController = RobotMap.FrontRight.getPIDController();
        m_backrightPodPIDController = RobotMap.BackRight.getPIDController();

        CANPIDController[] wheels = {m_backrightPodPIDController, m_backleftPodPIDController, m_frontrightPodPIDController, m_frontleftPodPIDController};

        for(int i = 0; i<=4; i++) { //Condensed code. Easier to write
            wheels[i].setP(Kp);
            wheels[i].setI(Ki);
            wheels[i].setD(Kd);
            wheels[i].setFF(KFF);
            //wheels[i].setIZone(KIz);
            wheels[i].setOutputRange(min, max);
        }

        RobotMap.FrontLeft.burnFlash();
        RobotMap.BackLeft.burnFlash();
        RobotMap.FrontRight.burnFlash();
        RobotMap.BackRight.burnFlash();

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

    //The next following functions will help whenever Motion profiling becomes a thing



    public void driveArcade(double translate, double rotate) {
        if(usingxbox)
            RobotMap.robotDrive.arcadeDrive(translate,rotate);
        //This can and will change dependent on what controller we use
    }

    public void stop() {
        RobotMap.robotDrive.arcadeDrive(0.0, 0.0);
    }

    //
}

