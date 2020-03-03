package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.autonomous.ExactMovementCommand;
import org.robockets.infiniterecharge.drivetrain.DriveStraightCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsAutoCommand;


public class MiddletoLowCommandGroup extends CommandGroup {

    /**
        This command specifically is for when the player starts up in the middle,
        and goes up to the low ball to shoot 3 balls out.
     */

    public MiddletoLowCommandGroup() {
        requires(Robot.Drivetrain);
        requires(Robot.Shooter);

        addSequential(new DriveStraightCommand(0.5,2.5));
        //addSequential(new ExactMovementCommand(120));
        addSequential(new MoveBallsAutoCommand(0.7,0.2,1.0,5)); //todo: resolve arbitrary values
        this.start();
    }
}
