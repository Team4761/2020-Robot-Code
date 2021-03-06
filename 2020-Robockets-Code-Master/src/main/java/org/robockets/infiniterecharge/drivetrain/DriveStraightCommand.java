package org.robockets.infiniterecharge.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class DriveStraightCommand extends Command {

    private double distance;

    public DriveStraightCommand(double speed, double seconds) {
        this.distance = speed;
        setTimeout(seconds);
        requires(Robot.Drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.Drivetrain.driveTank(distance, distance);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return isTimedOut();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
