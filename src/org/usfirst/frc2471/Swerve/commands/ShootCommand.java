/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2471.Swerve.Robot;

/**
 *
 * @author FIRST
 */
public class ShootCommand extends Command {
    
    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooterFan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooterFan.Shoot(-1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.shooterFan.Shoot(0.0);
    }
}
