package org.robockets.infiniterecharge.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
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
    protected void execute() { //todo: test controls
        Robot.Shooter.intake(OI.button104.get() ? 0.6 : 0.0);

        if(OI.button104.get() && !RobotMap.intakeBreakBeam.get() && RobotMap.flywheelBreakBeam.get())
            Robot.Shooter.movePolyCord(0.85);
        else
            Robot.Shooter.movePolyCord(0.0);

        //what are the chances that this runs when it shouldn't?
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
