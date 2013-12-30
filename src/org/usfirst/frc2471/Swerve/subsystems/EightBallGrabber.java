/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2471.Swerve.RobotMap;

/**
 *
 * @author FIRST
 */
public class EightBallGrabber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Solenoid lifter;
    public SpeedController intake;
    
    public EightBallGrabber() {
        lifter = RobotMap.ballLifter;
        intake = RobotMap.ballIntake;
    }
    
    public void suck() {
        intake.set(-0.75);
    }

    public void spit() {
        intake.set(0.75);
    }
    
    public void lift() {
        lifter.set(true);
    }
    
    public void reset() {
        intake.set(0.0);
        lifter.set(false);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
 