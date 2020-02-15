package org.robockets.infiniterecharge.autonomous.commandgroups;


import edu.wpi.first.wpilibj.command.CommandGroup;
import org.robockets.infiniterecharge.drivetrain.DriveStraightCommand;


public class TheJerkCommandGroup extends CommandGroup {
    public TheJerkCommandGroup() {
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
        addSequential(new DriveStraightCommand(-1, 2 ));
        addSequential(new DriveStraightCommand(0.7, 0.5));
    }
}