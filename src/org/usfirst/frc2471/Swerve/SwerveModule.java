// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc2471.Swerve;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class SwerveModule implements Runnable {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private SpeedController twist;
    private SpeedController speed;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Thread t;
    Encoder twistEnc, speedEnc;
    PIDController twistController;
    double twistOffset = 0.0;
    String name;
    static DashboardPID twistDashboardPID;
    
    public SwerveModule(String _name, SpeedController _speedController, Encoder _speedEnc, SpeedController _twistController, Encoder _twistEnc) {
        //indexEnc = new DigitalInput(3);
        name = _name;
        speed = _speedController;
        speedEnc = _speedEnc;
        twist = _twistController;
        twistEnc = _twistEnc;
        
//        twistEnc.setDistancePerPulse(Math.PI * 2.0 / 1250.0);
//        twistEnc.start();
        
//        System.out.println("ServeModule Constructed" + name);
        
        twistController = new PIDController(1.0, 0.0, 0.5, new PidThing(this), new PidThing(this));
        twistDashboardPID = new DashboardPID("Twist", twistController);
        twistController.setInputRange(-Math.PI, Math.PI);
        twistController.setOutputRange(-1.0, 1.0);
        twistController.setContinuous(true);
        twistController.setPercentTolerance(1.0);  //1=1%  15==15% etc  verified with docs
        
        t = new Thread(this);
        t.start();
    }
    
    public DashboardPID getTwistDashboardPID() {
        return twistDashboardPID;
    }
    
    public void setTwistOffset(double newVal) {
        twistOffset = newVal;
    }
    /**
     * @return the twist
     */
    public double getTwist() {
        double rtn = twistEnc.getDistance();
//        System.out.println(name + " SM Distance: " + rtn);
        rtn = rtn + twistOffset;
        while(rtn > Math.PI) {
            rtn = rtn - 2.0 * Math.PI;
        }
        while(rtn < -1.0 * Math.PI) {
            rtn = rtn + 2.0 * Math.PI;
        }
//        double angle = rtn / Math.PI * 180.0 + 180.0;
//        SmartDashboard.putNumber("encoder", angle);
        return rtn;
    }

    /**
     * @param twist the twist to set
     */
    public void setTwist(double twist) {
       //System.out.println("SM twist: " + twist);
       twistController.setSetpoint(twist);
       twistDashboardPID.update();
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed.get();
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed.set(speed);
    }
    
    double upperDeadBand = 0.15; // Was 0.15
    double lowerDeadBand = 0.1;
    double lastMotor = 0.0;
    public void setTwistMotor(double _motor) {
        double motor = _motor;
//        boolean stalled = (Math.abs(twistEnc.getRate()) < 1.0);
//        if(lastMotor < 0.05 && stalled) {
//            lowerDeadBand = lowerDeadBand + 0.01;
//        }
//        if(lastMotor > 0.05 && stalled) {
//            upperDeadBand = upperDeadBand + 0.01;
//        }
//        if (motor < 0) {
//            motor = (1.0 - lowerDeadBand) *_motor - lowerDeadBand;
//        }
//        else if (motor > 0) {
//            motor = (1.0 - upperDeadBand)*_motor + upperDeadBand;
//        }
//        else{
//            motor = 0;
//        }
        lastMotor = motor;
        twist.set(motor * -1.0);
//        System.out.println(name + " SM Turn: " + motor * -1.0);
    }
    public void run() {
        twist.set(0.75);
        double last;
        
//        System.out.println("Home Start: " + name);        
        do {
            last = twistEnc.getDistance();
            try {                    
                Thread.sleep(25);
                if(!DriverStation.getInstance().isDisabled()) {
//                    System.out.println(name + "Twist="+last);
                }
            }
            catch(Exception e) {
                System.out.println("SwerveModule thread sleep exception");
            }
        } while(twistEnc.getDistance() - last < 0.01);
//        System.out.println("Home Ended: " + name);        
        twist.set(0.0);
//        twistEnc.reset();
        twistController.enable();
        setTwist(0.0);
        //SmartDashboard.getDouble("Error",last);
//        SmartDashboard.putDouble("Twist power",0.0);
//        while(true) {
//            double motor = SmartDashboard.getDouble("Twist power");
//            setTwistMotor(motor);
//            try {                    
//                Thread.sleep(1);
//                //System.out.println("Twist="+last);
//            }
//            catch(Exception e) {
//                //System.out.println("SwerveModule thread sleep exception");
//            }
//            System.out.println("Twist rate: " + twistEnc.getRate());
//        }
    }
    
    class PidThing implements PIDSource, PIDOutput {
        SwerveModule master;
        public PidThing(SwerveModule _master) {
            master = _master;
        }
        public double pidGet() {
            //SmartDashboard.putNumber("Twist Error", twistController.getError());
            //System.out.println(name + "Twist: " + getTwist());
            return master.getTwist();
        }
        public void pidWrite(double out) {
            master.setTwistMotor(out);
//            System.out.println(master.name + "pidWrite: " + out);
        }
    }
}
