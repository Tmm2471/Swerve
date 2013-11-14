/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author FIRST
 */
public class SwerveVector {
    private SwerveModule swerve;
    private double Px, Py;
    private double hyp, xOffset = 0.0, yOffset = -25;
    private double desiredPower = 0;
    private double handsOffAngle = 0;
    
    public SwerveVector(SwerveModule _swerve, double xPosition, double yPosition, double _handsOffAngle) {
        swerve = _swerve;
        Px = xPosition;
        Py = yPosition;
        hyp = Math.sqrt((Px+xOffset)*(Px+xOffset) + (Py+yOffset)*(Py+yOffset));
        handsOffAngle = _handsOffAngle;
    }
    
    double Rx(double rotation) {
        return (rotation * (Py + yOffset) / hyp);
    }
    double Ry(double rotation) {
        return (rotation * -(Px + xOffset) / hyp);
    }
    public void setXOffset(double newX) {
        xOffset = newX;
        hyp = Math.sqrt((Px+xOffset)*(Px+xOffset) + (Py+yOffset)*(Py+yOffset));
    }
    public void setYOffset(double newY) {
        yOffset = newY;
        hyp = Math.sqrt((Px+xOffset)*(Px+xOffset) + (Py+yOffset)*(Py+yOffset));
    }
    public double drive(double x, double y, double r) {
        double vecX, vecY;
        double cappedTurn = r * 0.7;
        vecX = x + Rx(cappedTurn);
        vecY = y + Ry(cappedTurn);
        // Convert vecX and vecY to polar coords
        double angle, magnitude;
        angle = MathUtils.atan2(-vecX, vecY);  // we want 0 degrees to be in the Positive Y direction, which is towards the front of the robot
        swerve.setTwist(angle);
        desiredPower = Math.sqrt(vecX*vecX+vecY*vecY);
        return desiredPower;
    }
    public void SetMaxPower( double maxPower )
    {
        swerve.setSpeed( desiredPower / maxPower );
    }
    
    public void HandsOff()
    {
        swerve.setTwist( handsOffAngle );
        swerve.setSpeed(0.0);
    }
}
