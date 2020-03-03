package org.robockets.infiniterecharge.shooter;

import edu.wpi.first.wpilibj.command.Command;
import org.robockets.infiniterecharge.OI;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;

public class MoveBallsCommand extends Command {

    private double flywheel;
    private double polycord;
    private double intake;

    public MoveBallsCommand(double polycord, double flywheel, double intake) { //this is designed differently so I can use auto code with
        this.polycord = polycord;
        this.flywheel = flywheel;
        this.intake = intake;
        requires(Robot.Shooter);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double flywheel;
        if (OI.button205.get()) flywheel = this.flywheel;
        else if (OI.button103.get()) flywheel = -this.flywheel;
        else if (OI.button111.get()) flywheel = 1.0;
        else flywheel = 0.0;

        double intake;
        if (OI.button203.get()) intake = -this.intake;
        else if (OI.button202.get()) intake = this.intake;
        else intake = 0.0;

        double polycord;
        if (OI.button105.get()) polycord = -this.polycord;
        else if (OI.button102.get()) polycord = this.polycord;
        else polycord = 0.0;

        Robot.Shooter.movePolyCord(polycord); //up and down
        Robot.Shooter.fireFlyWheel(flywheel); //one of the triggers
        Robot.Shooter.intake(intake); //If it only needs to be a one way input, then we're fine

    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        Robot.Shooter.movePolyCord(0.0);
        Robot.Shooter.fireFlyWheel(0.0);
        Robot.Shooter.intake(0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
