package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class MovePistonCommand extends Command {

    private boolean pos;

    public MovePistonCommand(boolean position) {
        this.pos = position;
        requires(Robot.Wheel);
    }

    @Override
    protected void initialize() {
        Robot.Wheel.MovePiston(pos);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
