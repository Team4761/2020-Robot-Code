package org.robockets.infiniterecharge.shooter;


import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.robockets.infiniterecharge.RobotMap;

public class ShooterSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    public int ballCount = 0;

    private final double GEARBOX_RATIO = 90.0;

    private final double REVS_PER_INCH = 1.0; //TODO: find an actual value
    private final double REVS_PER_DEGREE = 0.0;

    private final double ABSOLUTE_TOLERANCE = 0.5; //inches

    private final double Kp = 0.0;
    private final double Ki = 0.0;
    private final double Kd = 0.0;

    private double setpoint;

    /**
     * The Singleton instance of this ShooterSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();

    /**
     * Creates a new instance of this ShooterSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private ShooterSubsystem() {
        //polyPID = new PIDController(Kp,Ki,Kd);

    }

    /**
     * Returns the Singleton instance of this ShooterSubsystem. This static method
     * should be used -- {@code ShooterSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveBallsCommand());
    }

    public void movePolyCord(double speed) {
        RobotMap.PolyCordControllerTop.set(ControlMode.Current,speed);
        RobotMap.PolyCordControllerBotton.set(ControlMode.Current,speed);
    }

    public void fireFlyWheel(double speed) {
        //RobotMap.FlyWheel1.set(speed);
        //RobotMap.FlyWheel2.set(speed);
    }

    /*public void movePolyCordExact(double inches) {
        this.setpoint = inches*REVS_PER_INCH;
        RobotMap.PolyCordController.set(polyPID.calculate(RobotMap.PolyCordController.get(),inches*REVS_PER_DEGREE ));
    }*/

    public boolean isBreakBeamBroke() {
        return false;
    }

    /*public boolean onTarget() { //onTarget with tolerance
        return (((RobotMap.PolyCordController.get()-ABSOLUTE_TOLERANCE)<=this.setpoint)&&((RobotMap.PolyCordController.get()+ABSOLUTE_TOLERANCE)>=this.setpoint));
    }*/

    public void intake(double speed) {
        RobotMap.InputWheel.set(ControlMode.Current,speed);
    }
}

