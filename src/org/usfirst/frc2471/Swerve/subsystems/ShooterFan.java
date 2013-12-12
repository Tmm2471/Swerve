/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2471.Swerve.RobotMap;

/**
 *
 * @author FIRST
 */
public class ShooterFan extends Subsystem {
    SpeedController speedController;
    
    public ShooterFan() {
        speedController = RobotMap.shooterFanController;
    }            
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void Shoot( double speed ) {
        speedController.set(speed);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
