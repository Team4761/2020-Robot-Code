package org.robockets.infiniterecharge.wheel;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;

@Deprecated(since = "2/19/2020", forRemoval = true)
public class DetectColorCommand extends Command {

    public DetectColorCommand() {
        requires(Robot.Wheel);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.Wheel.color = Robot.Wheel.getColorString(Robot.Wheel.getColor());
        //System.out.println(colorString);

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        end();
    }
}
