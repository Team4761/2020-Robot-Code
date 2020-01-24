package org.robockets.infiniterecharge.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;

public class DriveStraightCommand extends Command {

    private double distance;

    public DriveStraightCommand(double distance) {
        this.distance = distance;
        requires(Robot.Drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.Drivetrain.driveTrank(distance, distance);
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

    }
}
