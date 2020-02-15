package org.robockets.infiniterecharge.climber;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;

public class TelescopeCommand extends Command {

    private double speed;

    public TelescopeCommand(double speed) {
        this.speed = speed;
        requires(Robot.Shooter);
        // If any subsystems are needed, you will need to pass them into the requires() method
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if(OI.b103.get()) Robot.Climber.telescope(speed);
        else if(OI.b104.get()) Robot.Climber.telescope(-speed);
        else Robot.Climber.reel(0.0);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Climber.telescope(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
