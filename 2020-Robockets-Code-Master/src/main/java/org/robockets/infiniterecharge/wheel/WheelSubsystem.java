package org.robockets.infiniterecharge.wheel;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import org.robockets.infiniterecharge.OI;
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

    public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public static final ColorSensorEncoder colorsensorEncoder = new ColorSensorEncoder(19);


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
        setDefaultCommand(new SpinWheelCommand());
    }

    public void moveArm() {
        //RobotMap.WheelArm.set(ControlMode.Position,OI.xbox.getRawAxis(4));
        //RobotMap.WheelArm.set(OI.xbox.getRawAxis(4));
        RobotMap.WheelArm.set(ControlMode.Current,OI.xbox.getRawAxis(4));

    }


    public void spin(double speed) {
        RobotMap.WheelSpinner.set(speed);
    }

    public int getColor() {
        ColorMatchResult match = new ColorMatch().matchClosestColor(RobotMap.ColorSensor.getColor());

        if (match.color == kBlueTarget) return 2;
        else if (match.color == kRedTarget) return 0;
        else if (match.color == kGreenTarget) return 3;
        else if (match.color == kYellowTarget) return 1;
        else return 4;

    }

    public int getAccurateColor() {
        return colorsensorEncoder.calculateAverageColor();
    }

}

