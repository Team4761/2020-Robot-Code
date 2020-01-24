package org.robockets.infiniterecharge.shooter;


import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

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
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       e.g. setDefaultCommand(new MyCommand());
    }
}

