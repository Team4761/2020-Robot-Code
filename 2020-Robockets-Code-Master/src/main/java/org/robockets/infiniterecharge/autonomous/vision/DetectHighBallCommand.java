package org.robockets.infiniterecharge.autonomous.vision;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;
import org.robockets.infiniterecharge.shooter.ShooterSubsystem;

public class DetectHighBallCommand extends Command {

    //The Robot Must be at least 30 feet away, and must be aimed towards the high ball

    public DetectHighBallCommand() {
        requires(Robot.Drivetrain);
        requires(Robot.Shooter);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {

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
