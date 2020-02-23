package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;

@Deprecated
public class MoveArmCommand extends Command {

    private double s;

    public MoveArmCommand(double speed) {
        requires(Robot.Wheel);
        this.s = speed;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double speed;

        if(OI.button213.get()) speed = this.s;
        else if(OI.button207.get()) speed = -this.s;
        else speed = 0.0;

        Robot.Wheel.moveArm(speed);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    protected void end() {
        Robot.Wheel.moveArm(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
