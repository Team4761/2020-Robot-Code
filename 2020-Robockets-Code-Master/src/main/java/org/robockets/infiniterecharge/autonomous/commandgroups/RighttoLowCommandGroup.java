package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.autonomous.MoveTimedCommand;
import org.robockets.infiniterecharge.autonomous.RotateCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsAutoCommand;


public class RighttoLowCommandGroup extends CommandGroup {
    public RighttoLowCommandGroup() {
        requires(Robot.Drivetrain);
        requires(Robot.Shooter);
        addSequential(new RotateCommand(-45)); //todo: resolve abitrary values
        //addSequential(new ExactMovementCommand());
        addSequential(new MoveTimedCommand(0.65,0,5));
        addSequential(new RotateCommand(45));
        addSequential(new MoveBallsAutoCommand(0.7,0.2,1.0,8));
        this.start();
    }
}
