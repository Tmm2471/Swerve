/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author FIRST
 */
public class SimpleAutonomous extends CommandGroup {
    
    public SimpleAutonomous() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        addSequential(new WaitCommand(1.0));
        addSequential(new ResetGyroCommand(Math.PI));
        addSequential(new DriveTimeCommand(3.75, 0.167, 0.5, 0.0, -1.0));
        addSequential(new WaitCommand(3.0));
        addSequential(new BunnyLaunch(), 1.0);        
    }
}
