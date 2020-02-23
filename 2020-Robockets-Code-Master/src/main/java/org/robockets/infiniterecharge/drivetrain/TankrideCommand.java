package org.robockets.infiniterecharge.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;

public class TankrideCommand extends Command {

    private final double RAMP_FACTOR = 0.5;

    private double previousLeft = 0.0;
    private double previousRight = 0.0;

    //the intelliJ frc plugin wants me to create another instance of drivetrain for each command. That's pointless and takes up memory.
    //It's at times like this that I wish that java had pointers and references like C++
    public TankrideCommand() {
        requires(Robot.Drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        //double left = -OI.flight1.getRawAxis(1)*DrivetrainSubsystem.ROTATE_SPEED;
        //double right = -OI.flight2.getRawAxis(1)*DrivetrainSubsystem.ROTATE_SPEED;

        /*left = ((left-previousLeft)*RAMP_FACTOR)+previousLeft;  //PID on the drivetrain might mess the ramp factor, so it's commented for now
        right = ((right-previousRight)*RAMP_FACTOR)+previousRight;*/

        //Robot.Drivetrain.driveTank(left, right);

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Drivetrain.driveTank(0.0,0.0);
    }

    @Override
    protected void interrupted() {

    }
}
