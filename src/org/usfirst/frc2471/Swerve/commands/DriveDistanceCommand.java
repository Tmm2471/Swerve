/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2471.Swerve.RobotMap;

/**
 *
 * @author FIRST
 */
public class DriveDistanceCommand extends Command {
    double distance;
    double x;
    double y;
    double r;
    double s;
    
    public DriveDistanceCommand( double _distance, double _x, double _y, double _r, double _s ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(RobotMap.swerve);
        
        x = _x;
        y = _y;
        r = _r;
        s = _s;
        distance = _distance;
        System.out.println("drive distance constructed.");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //setTimeout(1.0);  // Hack:  let's just go for time for now.
        RobotMap.swerve.resetDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double gyroAngle = -RobotMap.gyro.getAngle() * (Math.PI/180.0);
        
        double dtg = distance - RobotMap.swerve.getTotalDistance();
        double multi = 1.0;
        if(dtg < 120.0) {
            multi = dtg/120.0;
            if(multi < 0.1){
                multi = 0.1;
            }
        }
        RobotMap.swerve.drive(x*multi, y*multi, r, s, gyroAngle, 0.0, 0.0, false, 0.0, true, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.swerve.getTotalDistance() > distance;
        //return isTimedOut();
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
