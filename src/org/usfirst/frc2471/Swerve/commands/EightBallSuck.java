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
public class EightBallSuck extends Command {
    
    public EightBallSuck() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.eightBallGrabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.eightBallGrabber.lift();
        Robot.eightBallGrabber.suck();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.eightBallGrabber.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.eightBallGrabber.reset();
    }
}
