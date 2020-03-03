package org.robockets.infiniterecharge.wheel;


import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import org.robockets.infiniterecharge.RobotMap;

public class WheelSubsystem extends Subsystem {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    //Porting all colors over to here ensures a streamline and 1 threaded color collection system.
    //The only time that the color is received is from Robot.periodic().Wheel.setColor();

    public String color = "Unknown";
    public int oncolor = 0;

    private final double REVS_PER_DEGREE = 8.57142857; //TODO: Confirm Value
    private final double WHEEL_TO_CONTROL_PANEL = 14.146/1.0; //wheel rotations : control panel
    private final double GEARBOX_RATIO = 90.0; //keep in mind with certain specific controls

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private final ColorMatch m_colorMatcher = new ColorMatch();
    final ColorSensorEncoder colorsensorEncoder = new ColorSensorEncoder(19);

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
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
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
        setDefaultCommand(new SpinWheelCommand(0.2,.5,.25));
    }

    public void moveArm(double value) {
        //RobotMap.WheelArm.set(ControlMode.Position,OI.xbox.getRawAxis(4));
        RobotMap.WheelArm.set(value);
        //RobotMap.WheelArm.set(ControlMode.Current,value);
    }

    public void spin(double speed) {
        RobotMap.WheelSpinner.set(speed);
        //RobotMap.WheelSpinner.set(RobotMap.wheelPIDController.calculate(RobotMap.WheelSpinner.get() , speed));
    }

    //getColor() --> ColorSensorEncoder.calculateAverageColor()
    // --> Wheel.getAverageColor() --> setColor() --> Shuffleboard && Wheel.oncolor
    //
    //Something about this feels like it can be simplified...

    public int getColor() {
        Color detect = RobotMap.ColorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detect);

        if (match.color == kBlueTarget) return 2;
        else if (match.color == kRedTarget) return 0;
        else if (match.color == kGreenTarget) return 3;
        else if (match.color == kYellowTarget) return 1;
        else return 4;
    }

    public String getColorString(int col) {
        switch(col) {
            case 2: return "Blue";
            case 0: return "Red";
            case 3: return "Green";
            case 1: return "Yellow";
            default: return "Unknown";
        }
    }

    public int getAccurateColor() {
        return colorsensorEncoder.calculateAverageColor();
        //return getColor();
    }

    public void setColor() {
        oncolor = getAccurateColor();
        color = getColorString(oncolor);
    }

}

