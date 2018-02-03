package org.usfirst.frc.team6201.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class Elevator extends Subsystem {

/*	
    private DigitalInput maxSwitch = new DigitalInput(0);
    private DigitalInput minSwitch = new DigitalInput(1);
    
    private WPI_TalonSRX elevator1 = new WPI_TalonSRX(7);
    private WPI_TalonSRX elevator2 = new WPI_TalonSRX(8);
    
    private Counter maxCounter = new Counter(maxSwitch);
    private Counter minCounter = new Counter(minSwitch);
    
    public boolean maxSwitchSet() {
    	
    	return maxCounter.get() > 0;
    	
    }
    
    public boolean minSwitchSet() {
    	
    	return minCounter.get() > 0;
    	
    }
	
    public void initializeCounters() {
    	
    	maxCounter.reset();
    	minCounter.reset();
    	
    }
    
    public void ascend() {
    	
    	elevator1.set(ControlMode.PercentOutput, 0.75);
    	elevator2.set(ControlMode.PercentOutput, 0.75);
    	
    }
    
    public void descend() {
    	
    	elevator1.set(ControlMode.PercentOutput, -0.75);
    	elevator2.set(ControlMode.PercentOutput, -0.75);
    	
    }
    
    public void stop() {
    	
    	elevator1.set(ControlMode.PercentOutput, 0.0);
    	elevator2.set(ControlMode.PercentOutput, 0.0);
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
*/    
    
}

