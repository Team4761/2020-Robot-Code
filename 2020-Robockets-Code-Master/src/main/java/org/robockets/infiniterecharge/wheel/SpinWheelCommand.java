package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.wheel.WheelSubsystem;

public class SpinWheelCommand extends Command {

    public SpinWheelCommand() {
        requires(Robot.Wheel);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.Wheel.spin(OI.xbox.getRawAxis(3));

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Wheel.spin(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
