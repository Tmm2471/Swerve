/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc2471.Swerve;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import java.lang.Thread;

/**
 *
 * @author FIRST
 */
public class CompressorControl implements Runnable {
    // private vars
    private Relay compressor;
    private DigitalInput pressureSwitch;

    // Constructor(s)
    CompressorControl(int chanCompressor, int chanPS) {
        this(chanCompressor, 1, 1, chanPS);
    }
    
    CompressorControl(int chanCompressor, int modComp, int modPS, int chanPS) {
        compressor = new Relay(modComp, chanCompressor, Relay.Direction.kForward);
        pressureSwitch = new DigitalInput(modPS, chanPS);

        // Start background task
        Thread t = new Thread(this);
        t.start();
    }
    
    CompressorControl(int chanCompressor, int modPS, int chanPS) {
        this(chanCompressor, 1, modPS, chanPS);
    }
    // Public Methods
    public void runner() {
       // while(true) {
            if(pressureSwitch.get() == false) {
                // low pressure, turn on compressor
                //System.out.println("Compressor on");
                compressor.set(Relay.Value.kOn);
            }
            else {
                // high pressure, turn off compressor
                //System.out.println("Compressor off");
                compressor.set(Relay.Value.kOff);
            }
      //  }
    }
    // Private Methods
    public void run() {
        while(true) {
            runner();
            Timer.delay(0.25);
        }
    }
}
