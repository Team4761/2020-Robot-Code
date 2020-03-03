package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.drivetrain.DriveStraightCommand;


public class TheJerkCommandGroup extends CommandGroup {
    public TheJerkCommandGroup() {
        requires(Robot.Drivetrain);

        addSequential(new DriveStraightCommand(-1, 1 ));
        addSequential(new DriveStraightCommand(0.7, 0.25));
        this.start();
    }
}
