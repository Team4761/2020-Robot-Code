package org.robockets.infiniterecharge.drivetrain;


import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import org.robockets.infiniterecharge.RobotMap;
import org.robockets.infiniterecharge.OI;

public class DrivetrainSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    //private final double INCHES_PER_TICK = 1.0; // TODO: Get an actual value

    private final double REVS_PER_INCH = 1.0; //TODO: Get an actual value
    private final double ABSOLUTE_TOLERANCE = 0.5; //inches. TODO: Get an actual value

    private final double REVS_PER_DEGREE = 8.57142857; //TODO: Verify value
    private final double ROT_ABSOLUTE_TOLERANCE = 5.0; //degrees.

    private CANPIDController m_frontleftPIDController;
    private CANPIDController m_backleftPIDController;

    private CANPIDController m_frontrightPIDController;
    private CANPIDController m_backrightPIDController;

    private CANPIDController[] wheels = {m_backrightPIDController, m_backleftPIDController, m_frontrightPIDController, m_frontleftPIDController};

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
    private DrivetrainSubsystem() { //TODO: regarding PID updating. Does anything need to be into periodic?
        m_frontleftPIDController = RobotMap.FrontLeft.getPIDController();
        m_backleftPIDController = RobotMap.BackLeft.getPIDController();

        m_frontrightPIDController = RobotMap.FrontRight.getPIDController();
        m_backrightPIDController = RobotMap.BackRight.getPIDController();

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
        setDefaultCommand(new JoyrideCommand());
    }

    public void driveArcade(double translate, double rotate) {
        RobotMap.RobotDrive.arcadeDrive(translate,rotate);
        //This can and will change dependent on what controller we use
    }

    public void driveTrank(double left, double right) {
         //implies we use The flight controllers
        RobotMap.RobotDrive.tankDrive(left,right);
    }

    public void stop() {
        RobotMap.RobotDrive.arcadeDrive(0.0, 0.0);
    }

    //The next following will orientate certain PID functions
    public void disablePid() { //will this even work? I'm worried about translate speed more than anything.
        m_frontleftPIDController.setReference(RobotMap.FrontLeft.getEncoder().getPosition()/REVS_PER_INCH*TRANSLATE_SPEED, ControlType.kPosition);
        m_backleftPIDController.setReference(RobotMap.BackLeft.getEncoder().getPosition()/REVS_PER_INCH*TRANSLATE_SPEED, ControlType.kPosition);
        m_frontrightPIDController.setReference(RobotMap.FrontRight.getEncoder().getPosition()/REVS_PER_INCH*TRANSLATE_SPEED, ControlType.kPosition);
        m_backleftPIDController.setReference(RobotMap.BackRight.getEncoder().getPosition()/REVS_PER_INCH*TRANSLATE_SPEED, ControlType.kPosition);
    }

    public void enablePid() {
        m_frontleftPIDController.setReference(RobotMap.FrontLeft.getEncoder().getPosition(), ControlType.kPosition);
        m_backleftPIDController.setReference(RobotMap.BackLeft.getEncoder().getPosition(), ControlType.kPosition);
        m_frontrightPIDController.setReference(RobotMap.FrontRight.getEncoder().getPosition(), ControlType.kPosition);
        m_backleftPIDController.setReference(RobotMap.BackRight.getEncoder().getPosition(), ControlType.kPosition);
    }

    public void disablePID() {

        for(int i = 0; i<=4; i++) { //Condensed code. Easier to write
            wheels[i].setP(0.0);
            wheels[i].setI(0.0);
            wheels[i].setD(0.0);
            wheels[i].setFF(0.0);
            //wheels[i].setIZone(KIz);
            //wheels[i].setOutputRange(min, max);
        }
    }

    public void enablePID() {

        for(int i = 0; i<=4; i++) { //Condensed code. Easier to write
            wheels[i].setP(Kp);
            wheels[i].setI(Ki);
            wheels[i].setD(Kd);
            wheels[i].setFF(KFF);
            //wheels[i].setIZone(KIz);
            //wheels[i].setOutputRange(min, max);
        }
    }

    public void setSetPoint() {
    }

    //The next set of commands are for when motion profiling becomes a thing
}

