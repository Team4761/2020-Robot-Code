package org.robockets.infiniterecharge.climber;


import edu.wpi.first.wpilibj.command.Subsystem;
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
    }

    public static void reelIn(){
        RobotMap.ReelInLeft.set(0.5); //will change
    }

    public static void reelOut(){
        RobotMap.ReelInLeft.set(-0.5); //will change
    }

    public void telescopeUp(){
        RobotMap.Telescope.set(0.5); //will change
    }

    public void telescopeDown(){
        RobotMap.Telescope.set(-0.5); //will change

    }

    public void stopReel(){
        RobotMap.ReelInLeft.set(0);
    }

    public void stopTele(){
        RobotMap.Telescope.set(0);
    }
    //button presses
    /*
    public static void bPressed(){
        reelIn.bPressed = true;
    }

    public static void bReleased(){
        reelIn.bPressed = false;
    }

    public static void aPressed(){
        telescopeIn.aPressed = true;
    }

    public static void aReleased(){
        telescopeIn.aPressed = false;
    }

    public static void xPressed(){
        telescopeOut.xPressed = true;
    }

    public static void xReleased(){
        telescopeOut.xPressed = false;
    }

    public static void yPressed(){
        reelOut.yPressed = true;
    }

    public static void yReleased(){
        reelOut.yPressed = false;
    }

     */
}

