package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class SpinWheelAutoCommand extends Command {

    private double degrees;

    public SpinWheelAutoCommand(double degrees) {
        requires(Robot.Wheel);
        this.degrees = degrees;
    }

    @Override
    protected void initialize() {
        Robot.Wheel.spinAuto(degrees);
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
        Robot.Wheel.spinAuto(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
