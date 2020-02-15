package org.robockets.infiniterecharge.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;

public class JoyrideCommand extends Command {

    private final double RAMP_FACTOR = 0.5;

    private double previousTranslate = 0.0;
    private double previousRotate = 0.0;

    public JoyrideCommand() {
        requires(Robot.Drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double translate = -OI.xbox.getRawAxis(1)*DrivetrainSubsystem.TRANSLATE_SPEED;
        double rotate = -OI.xbox.getRawAxis(0)*DrivetrainSubsystem.ROTATE_SPEED;

        /*translate = ((translate-previousTranslate)*RAMP_FACTOR)+previousTranslate; //PID systems may mess this over, so here. I just kept it simple
        rotate = ((rotate-previousRotate)*RAMP_FACTOR)+previousRotate;

        previousTranslate = translate;
        previousRotate = rotate;*/

        Robot.Drivetrain.driveArcade(translate, rotate);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Drivetrain.driveStop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
