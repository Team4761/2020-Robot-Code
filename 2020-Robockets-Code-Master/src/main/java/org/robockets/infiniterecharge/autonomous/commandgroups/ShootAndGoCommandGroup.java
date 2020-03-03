package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DriveStraightCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsAutoCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsCommand;


public class ShootAndGoCommandGroup extends CommandGroup { //the seconds are currently arbitrary
    public ShootAndGoCommandGroup() {
        requires(Robot.Shooter);
        requires(Robot.Drivetrain);

        addSequential(new MoveBallsAutoCommand(0.0,0.0,0.6,2)); //maybe???
        addSequential(new MoveBallsAutoCommand(1.0,0.0,0.6,5));
        addSequential(new DriveStraightCommand(0.4,1));
        this.start();
    }
}
