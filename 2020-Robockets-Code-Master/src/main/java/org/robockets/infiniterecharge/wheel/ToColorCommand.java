package org.robockets.infiniterecharge.wheel;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpiutil.math.Matrix;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;

public class ToColorCommand extends Command {
    //TODO: Clean up DetectColor and ToColor so I don't have to write code twice
    //This is a one-time command that can go into OI, it's not continuous like Joyride will be

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    //private final String[] pattern = {"Red","Yellow","Blue","Green","Unknown"};
    //private final int[] colorenum = {0,1,2,3,4};

    private int oncolor;
    private int tocolor;

    private final int[][] move = //Control Panel degrees Matrix
            {
                    {0,45,90,-45}, //red
                    {-45,0,45,90}, //yellow
                    {90,-45,0,45}, //blue
                    {45,90,-45,0} //green
            };


    public ToColorCommand() {
        requires(Robot.Wheel);
    }

    /**
     * One VERY important thing that needs to be taken care of here is where we go once we know the color
     * If we get blue, and need to go to yellow. We need to figure out which we way we rotate to get to that color the fastest
     * On top of that, we still don't know if out colorsensor is going to be above or below the color wheel.
     * If so, we'll need to invert some of our math, so ya. It's ALWAYS more complicated than what's on the surface
     */

    //From Top: Color pattern is: Red, yellow, blue, green (going clockwise)
    //From Bottom: Color pattern is: green, blue, yellow, red (going clockwise)
    //I will code for both possible situations

    @Override
    protected void initialize() {

        //Next Color
        switch(DriverStation.getInstance().getGameSpecificMessage()) {
            case "R":
                tocolor = 0;
            case "G":
                tocolor = 1;
            case "B":
                tocolor = 2;
            case "Y":
                tocolor = 3;
            default:
                tocolor = 4;

        }

        //Current Color
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

        //TODO: This will happen for 1 cycle only in initialize. Latency MIGHT be a problem...
        Color detectedColor = RobotMap.ColorSensor.getColor();

        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            oncolor = 2;
        } else if (match.color == kRedTarget) {
            oncolor = 0;
        } else if (match.color == kGreenTarget) {
            oncolor = 1;
        } else if (match.color == kYellowTarget) {
            oncolor = 3;
        } else {
            oncolor = 4;
        }

    }

    @Override
    protected void execute() {
        Robot.Wheel.spinAuto(move[oncolor][tocolor]);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Robot.Wheel.isOnTarget();
    }

    @Override
    protected void end() {
        Robot.Wheel.spinAuto(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
