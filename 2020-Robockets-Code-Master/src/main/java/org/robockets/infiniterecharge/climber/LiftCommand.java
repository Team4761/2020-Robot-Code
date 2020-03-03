package org.robockets.infiniterecharge.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.climber.ClimberSubsystem;

public class LiftCommand extends Command {

    private double reel;
    private double lift;

    public LiftCommand(double reelspeed, double liftspeed) {
        requires(Robot.Climber);
        this.lift = liftspeed;
        this.reel = reelspeed;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double reelrun;
        double liftrun;

        if(OI.button210.get()) reelrun = reel;
        else if(OI.button204.get()) reelrun = -reel;
        else reelrun = 0.0;

        if(OI.button206.get()) liftrun = lift;
        else if(OI.button113.get()) liftrun = -lift;
        else liftrun = 0.0;

        Robot.Climber.reel(reelrun);
        Robot.Climber.telescope(liftrun);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Climber.reel(0.0);
        Robot.Climber.telescope(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
