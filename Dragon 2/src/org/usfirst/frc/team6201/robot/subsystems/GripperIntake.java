package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * Automatically starts and stops the motors on the gripper when a cube
 * is near and when a cube is actually in the gripper, respectively.
 * 
 * Mysteriously commented out... why? 
 * 
 * @author Baxter Ellard
 * 
 */
public class GripperIntake extends Subsystem {
	
    //private WPI_TalonSRX leftIntake = new WPI_TalonSRX(5);
    //private WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
    /**
     * Ultrasonic sets up the ultrasonic sensor to be read as analog input.
     */
    private AnalogInput ultrasonic = new AnalogInput(RobotMap.ULTRASONIC);
    // factor to convert sensor values to a distance in inches
    private static final double valueToInches = 0.125;
    
    public GripperIntake() {
    	
    	//leftIntake = new WPI_TalonSRX(RobotMap.GRIPPER_LEFT);
    	//rightIntake = new WPI_TalonSRX(RobotMap.GRIPPER_RIGHT);
    	
    	//rightIntake.setInverted(true);
    	
    }
    
    /**
     * Checks if the target is within a certain distance and starts the 
     * motors accordingly.
     */
    public void startWheels() {
    	
    	// distance from the sensor to the target
    	double targetDistance = ultrasonic.getValue() * valueToInches;
    	// distance at which we want the wheels to start turning
    	// equivalent to 2.5 feet
    	double startDistance = 30.0;
    	// distance at which we want the wheels to stop turning
    	double stopDistance = 3.0;
    	
    	// checks if the distance from the target is both less than or equal to 2.5 feet
    	// and if the distance from the target is greater than 3 inches
    	if(targetDistance <= startDistance && targetDistance > stopDistance) {
    		
    		// if yes, set motor speed to 1
    		//leftIntake.set(ControlMode.PercentOutput, 1.0);
    		//rightIntake.set(ControlMode.PercentOutput, 1.0);
    		
    	} else {
    		
    		// if no, set motor speed to 0
    		//leftIntake.set(ControlMode.PercentOutput, 0.0);
    		//rightIntake.set(ControlMode.PercentOutput, 0.0);
    		
    	}
    	
    }
    
    public void initDefaultCommand() {
    }
}

