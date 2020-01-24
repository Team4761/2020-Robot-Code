package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class SpinWheelAutoCommand extends Command {

    private double degrees;
    private boolean direction;

    public SpinWheelAutoCommand(double degrees, boolean direction) {
        requires(Robot.Wheel);
        this.degrees = degrees;
        this.direction = direction;
    }

    @Override
    protected void initialize() {
        Robot.Wheel.spin(degrees,direction);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Robot.Wheel.isOnTarget();
    }

    @Override
    protected void end() {
        Robot.Wheel.spin(0.0,false);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
