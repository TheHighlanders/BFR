package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {

	private AnalogInput ultrasonic = new AnalogInput(RobotMap.ULTRASONIC);
	
	private static final double valueToInches = 0.125;

    public void initDefaultCommand() {

    	
    	
    }
    
    public double getInches() {
    	
    	return ultrasonic.getValue() * valueToInches;
    	
    }
}

