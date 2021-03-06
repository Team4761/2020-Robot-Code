
package org.robockets.infiniterecharge.climber;


import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.RobotMap;

public class ClimberSubsystem extends Subsystem {

    // Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.
    private final double GEARBOX_RATIO = 90.0;

    /**
     * The Singleton instance of this ClimberSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static ClimberSubsystem INSTANCE = new ClimberSubsystem();

    /**
     * Creates a new instance of this ClimberSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private ClimberSubsystem() {

    }

    /**
     * Returns the Singleton instance of this ClimberSubsystem. This static method
     * should be used -- {@code ClimberSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static ClimberSubsystem getInstance() {
        return INSTANCE;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       e.g. setDefaultCommand(new MyCommand());
        //setDefaultCommand(new TelescopeCommand(0.5));
        setDefaultCommand(new LiftCommand(1.0, 1.0));
    }

    //TODO: get better numbers with experimentation
    public void reel(double speed){
        if(OI.button107.get()) RobotMap.ReelInLeft.set(speed);
        if(OI.button106.get()) RobotMap.ReelInRight.set(-speed);
        else {
            RobotMap.ReelInLeft.set(speed);
            RobotMap.ReelInRight.set(-speed);
        }
    }

    public void telescope(double speed){
        RobotMap.Telescope.set(speed); //will change
    }
}