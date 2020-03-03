package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.shooter.MoveBallsAutoCommand;
import org.robockets.infiniterecharge.shooter.MoveBallsCommand;
import org.robockets.infiniterecharge.wheel.SpinWheelCommand;


public class TestAutoCommandGroup extends CommandGroup {
    public TestAutoCommandGroup() {
        MoveBallsAutoCommand ballsauto = new MoveBallsAutoCommand(0.6,0.0,0.0,2);
        MoveBallsCommand balls = new MoveBallsCommand(0.6,0.0,0.0);
        requires(Robot.Shooter);

        addSequential(ballsauto);
        this.start();
        System.out.println(ballsauto.isRunning());
    }
}
