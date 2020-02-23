package org.robockets.infiniterecharge.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class RotateCommand extends Command {

    private double rotation;

    public RotateCommand(double degrees) {
        requires(Robot.Drivetrain);
        this.rotation = degrees;
    }

    @Override
    protected void initialize() {
        Robot.Drivetrain.resetGyro();
    }

    @Override
    protected void execute() {
        Robot.Drivetrain.rotateDegrees(rotation);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Robot.Drivetrain.gyroOnTarget();
    }

    @Override
    protected void end() {
        Robot.Drivetrain.rotateDegrees(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
