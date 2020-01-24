package org.robockets.infiniterecharge.climber;


import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

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
    }
}

