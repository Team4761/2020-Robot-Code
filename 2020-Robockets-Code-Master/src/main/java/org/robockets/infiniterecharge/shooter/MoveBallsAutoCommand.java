package org.robockets.infiniterecharge.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.shooter.ShooterSubsystem;

public class MoveBallsAutoCommand extends Command {

    private double polycordspeed, flywheelspeed, intakespeed;
    private double timeout;

    public MoveBallsAutoCommand(double polycordspeed, double intakespeed, double flywheeelspeed, double seconds) {
        requires(Robot.Wheel);
        this.polycordspeed = polycordspeed;
        this.intakespeed = intakespeed;
        this.flywheelspeed = flywheeelspeed;
        this.timeout = seconds;
    }

    @Override
    protected void initialize() {
        setTimeout(timeout);
    }

    @Override
    protected void execute() {
        Robot.Shooter.movePolyCord(-polycordspeed); //up and down
        Robot.Shooter.fireFlyWheel(flywheelspeed); //one of the triggers
        Robot.Shooter.intake(intakespeed); //If it only needs to be a one way input, then we're fine
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.Shooter.movePolyCord(0.0); //up and down
        Robot.Shooter.fireFlyWheel(0.0); //one of the triggers
        Robot.Shooter.intake(0.0); //If it only needs to be a one way input, then we're fine

    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
