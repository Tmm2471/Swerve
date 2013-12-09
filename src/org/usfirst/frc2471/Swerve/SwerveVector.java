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
    private double hyp, xPivot = 0.0, yPivot = 0;
    private double desiredPower = 0;
    private double desiredAngle = 0;
    private double handsOffAngle = 0;
    
    public SwerveVector(SwerveModule _swerve, double xPosition, double yPosition, double _handsOffAngle) {
        swerve = _swerve;
        Px = xPosition;
        Py = yPosition;
        hyp = Math.sqrt((Px+xPivot)*(Px+xPivot) + (Py+yPivot)*(Py+yPivot));
        handsOffAngle = _handsOffAngle;
    }
    
    public SwerveModule getSwerveModule() {
        return swerve;
    }
    
    double Rx(double rotation) {
        return (rotation * (Py + yPivot) / hyp);
    }
    double Ry(double rotation) {
        return (rotation * -(Px + xPivot) / hyp);
    }
    public void setXOffset(double newX) {
        xPivot = newX;
        hyp = Math.sqrt((Px+xPivot)*(Px+xPivot) + (Py+yPivot)*(Py+yPivot));
    }
    public void setYOffset(double newY) {
        yPivot = newY;
        hyp = Math.sqrt((Px+xPivot)*(Px+xPivot) + (Py+yPivot)*(Py+yPivot));
    }
    
    class Polar {
        double r;
        double theta;

        public Polar(double x, double y){
            r = Math.sqrt(x*x + y*y);
            theta = MathUtils.atan2(-x, y); // we want 0 degrees to be in the Positive Y direction, which is towards the front of the robot
        }

        public double GetR()
        {
            return r;
        }

        public double GetAngle()
        {
            return theta;
        }

        public double GetX() {
            return -r*Math.sin(theta);
        }

        public double GetY() {
            return r*Math.cos(theta);
        }

        public void AddAngle( double angle ){
            theta = theta + angle;
        }
    }

    public double drive(double x, double y, double turnPower, double gyroAngle)
    {
        double reducedTurn = turnPower * 0.7;  // diminish turning, it was too fast
        
        Polar polar = new Polar(x,y);   // convert strafe to polar
        polar.AddAngle( -gyroAngle );  // add to convert the strafe request from field to robot space

        double vecX = polar.GetX() + Rx(reducedTurn);  // combine strafing with turning via vector addition
        double vecY = polar.GetY() + Ry(reducedTurn);
        
        // Convert vecX and vecY back to polar coords
        polar = new Polar( vecX, vecY );
        desiredAngle = polar.GetAngle();
        desiredPower = polar.GetR();
        FindNearestAngle( swerve.getTwist() );  // modifies both desiredAngle and desiredPower, prevents rotation of more than 90 degrees, reverses power if necessary
        
        swerve.setTwist(desiredAngle);
        return Math.abs(desiredPower);
    }
    public void SetMaxPower( double maxPower )
    {
        //swerve.setSpeed( desiredPower / maxPower );
    }
    
    public void HandsOff()
    {
        swerve.setTwist( handsOffAngle );
        swerve.setSpeed(0.0);
    }
    
    void FindNearestAngle( double currentAngle )
    {
        double delta = desiredAngle - currentAngle;
        if (delta>Math.PI)
        {
            delta = delta - 2*Math.PI;
        }
        else if (delta<-Math.PI)
        {
            delta = delta + 2*Math.PI;
        }
        
        if (delta>Math.PI/2)
        {
            delta = delta - Math.PI;
            desiredAngle = currentAngle + delta;
            desiredPower = -desiredPower;
        }
        else if (delta<-Math.PI/2)
        {
            delta = delta + Math.PI;
            desiredAngle = currentAngle + delta;
            desiredPower = -desiredPower;
        }
    }
}
