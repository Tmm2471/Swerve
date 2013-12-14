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
    public ArduinoEncoder(I2C _arduino, char _c) {
        arduino = _arduino;
        c = _c;
    }
    
    public int get() {
        byte[] inBuff = new byte[4];
        byte[] outBuff = new byte[1];
        long enc;
        outBuff[0] = (byte)c;
        arduino.transaction(outBuff, 1, inBuff, 0);        
        arduino.transaction(outBuff, 0, inBuff, 4);
        enc = ((long)((inBuff[0] & 0xff) << 24));
        enc = enc | ((long)((inBuff[1] & 0xff) << 16));
        enc = enc | ((long)((inBuff[2] & 0xff) << 8));
        enc = enc | ((long)((inBuff[3] & 0xff)));
        return (int)enc;
    }
}
