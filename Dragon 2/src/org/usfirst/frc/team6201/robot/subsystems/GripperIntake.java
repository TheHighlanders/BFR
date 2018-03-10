package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Uses ultrasonic sensors and TalonSRX motor controllers to automatically 
 * start and stop the motors on the gripper when a cube is near and when a 
 * cube is actually in the gripper, respectively. 
 * 
 * Start wheels may end up overriding the eject command
 * 
 * @author Baxter Ellard
 */
public class GripperIntake extends Subsystem {
	
	// Instantiates TalonSRX motor controllers at CAN Ports
	// 5 and 6.
	private VictorSP gripLeft = new VictorSP(RobotMap.GRIPPER_LEFT);
    private VictorSP gripRight = new VictorSP(RobotMap.GRIPPER_RIGHT);
	
    /**
     * Ultrasonic sets up the ultrasonic sensor to be read as analog input.
     */
    private AnalogInput ultrasonic = new AnalogInput(0);
    
    // factor to convert sensor values to a distance in inches
    private static final double valueToInches = 0.125;
    
    // equivalent to 2.5 feet
	private double startDistance = 30.0;
	// distance at which we want the wheels to stop turning
	private double stopDistance = 3.0;
	
	double targetDistance;
    
    /**
     * Constructor, sets up motors and ultrasonic sensor.
     */
    public GripperIntake() {
   
    	
    	ultrasonic = new AnalogInput(RobotMap.ULTRASONIC);
    	
    }
    
    /**
     * Checks if the target is within a certain distance and starts the 
     * motors accordingly.
     */
    public void startWheels() {
    	
    	// distance from the sensor to the target
    	targetDistance = ultrasonic.getValue() * valueToInches;
    	// distance at which we want the wheels to start turning
    	
    	
    	// checks if the distance from the target is both less than or equal to 2.5 feet
    	// and if the distance from the target is greater than 3 inches
    	if(targetDistance <= startDistance && targetDistance > stopDistance) {
    		
    		// if yes, set motor speed to 1
    		gripLeft.set(1.0);
    		gripRight.set(-1.0);
    		
    	} else {
    		
    		// if no, set motor speed to 0
    		gripLeft.set(0.0);
    		gripRight.set(0.0);
    		
    	}
    	
    }
    
    public double getUltrasonicDistance() {
    	
    	return ultrasonic.getValue() * valueToInches;
    	
    }
    
    /**
	 *  sucks in or maybe ejects out
	 */
   public void pull() {
   	
    gripLeft.set(1);
   	gripRight.set(-1);
   	
   }
   
   /**
    * ejects out or maybe sucks in
    */
   public void push() {
   	
	   gripLeft.set(-0.75);
	   gripRight.set(0.75);
   }
   
   /**
    * Sets gripper motor speeds to 0
    */
   public void stop() {
   	
	   gripLeft.set(0);
	   gripRight.set(0);
   }
    
    public void initDefaultCommand() {
    }
}

