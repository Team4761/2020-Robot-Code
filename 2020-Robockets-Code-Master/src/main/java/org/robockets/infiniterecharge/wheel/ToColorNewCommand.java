package org.robockets.infiniterecharge.wheel;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;

import static org.robockets.infiniterecharge.wheel.WheelSubsystem.*;

public class ToColorNewCommand extends Command {

    private final ColorMatch m_colorMatcher = new ColorMatch();


    private int oncolor;
    private int tocolor;

    public ToColorNewCommand() {
        requires(Robot.Wheel);
    }

    @Override
    protected void initialize() {
    //Next Color
        switch(DriverStation.getInstance().getGameSpecificMessage()) {
            case "R": tocolor = 0;
            case "G": tocolor = 3;
            case "B": tocolor = 2;
            case "Y": tocolor = 1;
            default: tocolor = 4;
        }

    }

    @Override
    protected void execute() {
        oncolor = Robot.Wheel.getAccurateColor();

        if(oncolor != tocolor)
            Robot.Wheel.spin(1.0);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return (oncolor==tocolor);
    }

    @Override
    protected void end() {
        Robot.Wheel.spin(0.0);
    }

    @Override
    protected void interrupted() {

    }
}
