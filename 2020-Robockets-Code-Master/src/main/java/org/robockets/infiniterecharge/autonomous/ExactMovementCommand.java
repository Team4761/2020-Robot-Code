package org.robockets.infiniterecharge.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class ExactMovementCommand extends Command {

    private double distance;

    public ExactMovementCommand(double inches) {
        requires(Robot.Drivetrain);
        this.distance = inches;
    }

    @Override
    protected void initialize() {
        Robot.Drivetrain.translateInches(distance);
        //Robot.Drivetrain.resetGyro();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Robot.Drivetrain.onTarget();
    }

    @Override
    protected void end() {
        Robot.Drivetrain.translateInches(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
