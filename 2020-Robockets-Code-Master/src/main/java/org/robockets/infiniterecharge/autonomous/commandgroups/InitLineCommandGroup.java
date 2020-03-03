package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DriveStraightCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsCommand;


public class InitLineCommandGroup extends CommandGroup {
    public InitLineCommandGroup() {
        requires(Robot.Drivetrain);
        requires(Robot.Shooter);

        addSequential(new DriveStraightCommand(0.4,1.3));
        this.start();
    }
}
