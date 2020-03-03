package org.robockets.infiniterecharge.climber;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;

@Deprecated
public class TelescopeCommand extends Command {

    private double speed;

    public TelescopeCommand(double speed) {
        this.speed = speed;
        requires(Robot.Climber);
        // If any subsystems are needed, you will need to pass them into the requires() method
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double run = 0.0;
        
        if(OI.button206.get()) run = -speed;
        else if(OI.button113.get()) run = speed;
        else run = 0.0;
        
        Robot.Climber.telescope(run);

        System.out.println("telescope run = " + run);
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
