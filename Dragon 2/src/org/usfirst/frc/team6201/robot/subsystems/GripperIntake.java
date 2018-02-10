package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Uses ultrasonic sensors and TalonSRX motor controllers to automatically 
 * start and stop the motors on the gripper when a cube is near and when a 
 * cube is actually in the gripper, respectively. 
 * 
 * @author Baxter Ellard
 */
public class GripperIntake extends Subsystem {
	
	// Instantiates TalonSRX motor controllers at CAN Ports
	// 5 and 6.
    private WPI_TalonSRX leftIntake = new WPI_TalonSRX(5);
    private WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
    /**
     * Ultrasonic sets up the ultrasonic sensor to be read as analog input.
     */
    private AnalogInput ultrasonic = new AnalogInput(0);
    
    // factor to convert sensor values to a distance in inches
    private static final double valueToInches = 0.125;
    
    // Distance from sensor to target in inches
    public double targetDistance = ultrasonic.getValue() * valueToInches;
    
    /**
     * Constructor, sets up motors and ultrasonic sensor.
     */
    public GripperIntake() {
    	
    	leftIntake = new WPI_TalonSRX(RobotMap.GRIPPER_LEFT);
    	rightIntake = new WPI_TalonSRX(RobotMap.GRIPPER_RIGHT);
    	
    	leftIntake.configOpenloopRamp(1, 0);
    	rightIntake.configOpenloopRamp(1, 0);
    	
    	ultrasonic = new AnalogInput(RobotMap.ULTRASONIC);
    	
    }
    
    /**
     * Checks if the target is within a certain distance and starts the 
     * motors accordingly.
     */
    public void autoWheelIntake() {
    	
    	// distance at which we want the wheels to start turning
    	// equivalent to 2.5 feet
    	double startDistance = 42.0;
    	// distance at which we want the wheels to stop turning
    	double stopDistance = 3.0;
    	
    	// checks if the distance from the target is both less than or equal to 2.5 feet
    	// and if the distance from the target is greater than 3 inches
    	if(targetDistance <= startDistance && targetDistance > stopDistance) {
    		
    		// if yes, set motor speed to 1
    		//leftIntake.set(1.0);
    		//rightIntake.set(-1.0);
    		
    	} else {
    		
    		// if no, set motor speed to 0
    		stop();
    		
    	}
    	
    }
    
    public void eject() {
    	
    	while(targetDistance < 16) {
    	
    		leftIntake.set(-1.0);
    		rightIntake.set(1.0);
    		
    	}
    	
    }
    
    public void stop() {
    	
    	leftIntake.set(0.0);
    	rightIntake.set(0.0);
    	
    }
    
    
    
    public void initDefaultCommand() {
    }
}

