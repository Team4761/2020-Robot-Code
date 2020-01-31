package org.robockets.infiniterecharge.wheel;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import org.robockets.infiniterecharge.PidSourceType;
import org.robockets.infiniterecharge.RobotMap;

public class WheelSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    public static String oncolor;
    public static String tocolor;

    private final double REVS_PER_DEGREE = 8.57142857; //TODO: Confirm Value
    private final double WHEEL_TO_CONTROL_PANEL = 14.146/1.0; //wheel rotations : control panel
    private final double GEARBOX_RATIO = 90.0; //keep in mind with certain specific controls

    public static final double ROTATE_SPEED = 0.85;

    private PIDController WheelSpinnerPID;
    PidSourceType type = PidSourceType.kdistance;

    private final double Kp = 0.0;
    private final double Ki = 0.0;
    private final double Kd = 0.0;
    private final double max = 1.0;
    private final double min = -1.0;
    private final double KIz = 0;
    private final double KFF = 0;

    /**
     * The Singleton instance of this wheelSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static WheelSubsystem INSTANCE = new WheelSubsystem();

    /**
     * Creates a new instance of this wheelSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private WheelSubsystem() {
        WheelSpinnerPID = new PIDController(Kp,Ki,Kd); //TODO: figure out if I need to normalize the number output
    }

    public void WheelSubsystemPeriodic() {
        RobotMap.WheelSpinner.set(WheelSpinnerPID.calculate(RobotMap.WheelSpinnerEncoder.getDistance())); //TODO: Implement gearbox ratios and such (is it even needed)?
    }

    /**
     * Returns the Singleton instance of this wheelSubsystem. This static method
     * should be used -- {@code wheelSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static WheelSubsystem getInstance() {
        return INSTANCE;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       e.g. setDefaultCommand(new MyCommand());
        setDefaultCommand(new SpinWheelCommand());
    }

    public void MovePiston(boolean position) {
        RobotMap.PistonArm.set(position);
    }

    public void spinAuto(double degrees) { //TODO: do IRL tests to see if I did the PID correct
        //RobotMap.WheelSpinner.set(direction? degrees*REVS_PER_DEGREE : -degrees*REVS_PER_DEGREE);
        WheelSpinnerPID.reset();
        WheelSpinnerPID.setSetpoint(degrees*REVS_PER_DEGREE);
    }

    public void spin(double speed) {
        RobotMap.WheelSpinner.set(speed*ROTATE_SPEED);
    }

    public boolean isOnTarget() {
        return WheelSpinnerPID.atSetpoint();
    }





}

