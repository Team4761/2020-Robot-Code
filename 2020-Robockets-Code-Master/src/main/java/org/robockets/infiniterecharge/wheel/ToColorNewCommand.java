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

    // {"Red","Yellow","Blue","Green","Unknown"}
    //private int[] colorenum = {0,1,2,3,4};
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
        //Move until on desired color
        if(Robot.Wheel.oncolor != tocolor) Robot.Wheel.spin(-0.2);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return ( Robot.Wheel.oncolor == tocolor );
    }

    @Override
    protected void end() {
        Robot.Wheel.spin(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
