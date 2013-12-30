/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2471.Swerve.RobotMap;

/**
 *
 * @author FIRST
 */
public class DriveTimeCommand extends Command {
    double time, stoptime;
    double x;
    double y;
    double r;
    double s;
    
    public DriveTimeCommand( double _time, double _x, double _y, double _r, double _s ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(RobotMap.swerve);
        
        x = _x;
        y = _y;
        r = _r;
        s = _s;
        time = _time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(time);
        RobotMap.swerve.resetDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double gyroAngle = -RobotMap.gyro.getAngle() * (Math.PI/180.0);
        RobotMap.swerve.drive(x, y, r, s, gyroAngle, 0.0, 0.0, false, 0.0, true, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        double gyroAngle = -RobotMap.gyro.getAngle() * (Math.PI/180.0);
        RobotMap.swerve.drive(0.0, 0.0, 0.0, 0.0, gyroAngle, 0.0, 0.0, false, 0.0, true, true);  // stop motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
