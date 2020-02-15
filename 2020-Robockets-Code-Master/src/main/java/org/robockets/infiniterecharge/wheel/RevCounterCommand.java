package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.wheel.WheelSubsystem;

public class RevCounterCommand extends Command {

    private boolean direction;
    private double rev;
    private double revmax;

    public RevCounterCommand(boolean direction, double revmax) {
        this.direction = direction;
        this.rev = 0.0;
        this.revmax = revmax;
        requires(Robot.Wheel);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        rev = WheelSubsystem.colorsensorEncoder.calculateRevs(direction);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return (revmax == rev);
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
