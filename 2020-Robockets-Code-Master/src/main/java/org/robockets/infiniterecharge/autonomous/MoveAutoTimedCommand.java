package org.robockets.infiniterecharge.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.Robot;

public class MoveAutoTimedCommand extends Command {

    private double translate;
    private double rotate;
    private double seconds;

    public MoveAutoTimedCommand(double move, double turn, double time) {
        requires(Robot.Drivetrain);
        this.translate = move;
        this.rotate = turn;
        this.seconds = time;
    }

    @Override
    protected void initialize() {
       setTimeout(seconds);
    }

    @Override
    protected void execute() {
        Robot.Drivetrain.driveArcade(translate, rotate);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return isTimedOut();

    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
