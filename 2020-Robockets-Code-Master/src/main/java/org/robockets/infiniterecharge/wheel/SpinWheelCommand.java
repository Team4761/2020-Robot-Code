package org.robockets.infiniterecharge.wheel;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.wheel.WheelSubsystem;

public class SpinWheelCommand extends Command {

    private double s, au, ad;

    public SpinWheelCommand(double wheelspeed, double armspeedup, double armspeeddown) {
        requires(Robot.Wheel);
        this.s = wheelspeed;
        this.au = armspeedup;
        this.ad = armspeeddown;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double speed;
        double speed1;

        if(OI.button212.get()) speed = this.s;
        else if(OI.button209.get()) speed = -this.s;
        else speed = 0.0;

        if(OI.button213.get()) speed1 = this.au;
        else if(OI.button207.get()) speed1 = -this.ad;
        else speed1 = 0.0;

        Robot.Wheel.spin(speed);
        Robot.Wheel.moveArm(speed1);

        //System.out.println(speed1);

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Wheel.spin(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
