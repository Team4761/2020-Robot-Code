package org.robockets.infiniterecharge.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;

public class IndexBallsCommand extends Command {

    public IndexBallsCommand() {
        requires(Robot.Shooter);
        // If any subsystems are needed, you will need to pass them into the requires() method
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if(RobotMap.intakeBreakBeam.get() && !RobotMap.flywheelBreakBeam.get())
            Robot.Shooter.movePolyCord(0.85);
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
