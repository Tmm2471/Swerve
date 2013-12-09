package org.usfirst.frc2471.Swerve;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FIRST
 */
public class DashboardPID {
    PIDController m_pidController;
    String pName, iName, dName;
    
    public DashboardPID( String PIDName, PIDController pidController ) {
        m_pidController = pidController;
        pName = PIDName+"P";
        iName = PIDName+"I";
        dName = PIDName+"D";
        Preferences prefs = Preferences.getInstance();
        double p = prefs.getDouble(pName, pidController.getP());
        double i = prefs.getDouble(iName, pidController.getI());
        double d = prefs.getDouble(dName, pidController.getD());
        SmartDashboard.putNumber(pName, p);
        SmartDashboard.putNumber(iName, i);
        SmartDashboard.putNumber(dName, d);
        pidController.setPID(p, i, d);        
    }
    
    public void update() {
        //SmartDashboard.putData(this);
        double p = SmartDashboard.getNumber(pName);
        double i = SmartDashboard.getNumber(iName);
        double d = SmartDashboard.getNumber(dName);
        m_pidController.setPID(p, i, d);
    }
    
    public void save() {
        Preferences prefs = Preferences.getInstance();
        prefs.putDouble(pName, m_pidController.getP());
        prefs.putDouble(iName, m_pidController.getI());
        prefs.putDouble(dName, m_pidController.getD());
    }
}
