/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2471.Swerve.RobotMap;

/**
 *
 * @author FIRST
 */
public class BunnyLaunch extends Command {
    Solenoid solenoid;
    
    public BunnyLaunch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        solenoid = RobotMap.bunnyLauncher;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        solenoid.set(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        solenoid.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        solenoid.set(false);
    }
}
