/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2471.Swerve;

/**
 *
 * @author FIRST
 */
public class Filter {
    double samples[];
    int pos;
    int count;
    int m_numSamples;
    double sum;
    
    public Filter( int numSamples ) {
        m_numSamples = numSamples;
        samples = new double[numSamples];
        pos = 0;
        count = 0;
        sum = 0;
    }
    
    public void AddSample( double sample ) {
        pos = pos + 1;
        if (pos == m_numSamples) {
            pos = 0;
        }
        
        count = count + 1;
        if (count>m_numSamples) {
            count = m_numSamples;
            sum = sum - samples[pos];
        }
            
        sum = sum + sample;
        samples[pos] = sample;
    }
    
    public double GetAverage() {
        return sum / count;
    }
}
