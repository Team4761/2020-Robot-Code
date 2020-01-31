package org.robockets.infiniterecharge.shooter;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.Robot;
import org.robockets.infiniterecharge.RobotMap;


public class IndexBallsCommandGroup extends CommandGroup {

    private MoveBallsCommand IntakeSequence;

    public IndexBallsCommandGroup() {
        requires(Robot.Shooter);
        /*
            Add Commands here:
            To run commands one after another, 
            use addSequential()
                e.g. addSequential(new Command1());
                     addSequential(new Command2());
                these will run in order.
            To run multiple commands at the same time,
            use addParallel()
                e.g. addParallel(new Command1());
                     addSequential(new Command2());
                Command1 and Command2 will run in parallel.
            
            A command group will require all the subsystems that each member would require.
                e.g. if Command1 requires 'chassis', and Command2 requires 'arm',
                a CommandGroup containing them would require both the chassis and the arm.
        */
        addSequential(IntakeSequence = new MoveBallsCommand(0.0, 0.0, 1.0));

        if(RobotMap.intakeBreakBeam.get() && !RobotMap.flywheelBreakBeam.get()) {
            addSequential(new MoveBallsCommand(0.85, 0.0, 0.0));
        }
    }
}
