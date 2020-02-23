package org.robockets.infiniterecharge.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DrivetrainSubsystem;

public class JoyrideCommand extends Command {

    private double slowmode;
    private double speed;

    private final double RAMP_FACTOR = 0.5;

    private double previousTranslate = 0.0;
    private double previousRotate = 0.0;

    double rot;

    public JoyrideCommand(double translatespeed, double rotatespeed, double slowspeed) {
        requires(Robot.Drivetrain);
        this.speed = translatespeed;
        this.rot = rotatespeed;
        this.slowmode = slowspeed;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if(OI.leftBumper.get()) Robot.Drivetrain.TRANSLATE_SPEED = slowmode;
        else Robot.Drivetrain.TRANSLATE_SPEED = speed;

        double translate = -OI.xbox.getRawAxis(1)*Robot.Drivetrain.TRANSLATE_SPEED;
        double rotate = -OI.xbox.getRawAxis(4)*rot;

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
