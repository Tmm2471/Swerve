/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2471.Swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;

/**
 *
 * @author Devil
 */
public class ArduinoEncoder {
    I2C arduino;
    char c;
    double distancePerPulse = 1.0;
    public ArduinoEncoder(I2C _arduino, char _c) {
        arduino = _arduino;
        c = _c;
    }
    
    public int get() {
        byte[] inBuff = new byte[4];
        byte[] outBuff = new byte[1];
        int enc;
        outBuff[0] = (byte)c;
        arduino.transaction(outBuff, 1, inBuff, 0);        
        arduino.transaction(outBuff, 0, inBuff, 4);
        enc = ((int)((inBuff[0] & 0xff) << 24));
        enc = enc | ((int)((inBuff[1] & 0xff) << 16));
        enc = enc | ((int)((inBuff[2] & 0xff) << 8));
        enc = enc | ((int)((inBuff[3] & 0xff)));
        return (int)enc;
    }
    
    public void setDistancePerPulse(double dist) {
        distancePerPulse = dist;
    }
    
    public double getDistance() {
        return distancePerPulse * (double) get();
    }
}
