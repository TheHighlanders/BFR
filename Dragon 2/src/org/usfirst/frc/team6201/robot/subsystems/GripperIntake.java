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
 * @author Baxter Ellard
 */
public class GripperIntake extends Subsystem {
	
	// Instantiates TalonSRX motor controllers at CAN Ports
	// 5 and 6.
	private VictorSP gripleft = new VictorSP(RobotMap.GRIPPER_LEFT);
    private VictorSP gripright = new VictorSP(RobotMap.GRIPPER_RIGHT);
	
    /**
     * Ultrasonic sets up the ultrasonic sensor to be read as analog input.
     */
    private AnalogInput ultrasonic = new AnalogInput(0);
    
    // factor to convert sensor values to a distance in inches
    private static final double valueToInches = 0.125;
    
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
    		//leftIntake.set(1.0);
    		//rightIntake.set(-1.0);
    		
    	} else {
    		
    		// if no, set motor speed to 0
    		//leftIntake.set(0.0);
    		//rightIntake.set(0.0);
    		
    	}
    	
    }
    
    /**
	 *  sucks in or maybe ejects out
	 */
   public void pull() {
   	
    gripleft.set(0.3);
   	gripright.set(-0.3);
   	
   }
   
   /**
    * ejects out or maybe sucks in
    */
   public void push() {
   	
	   gripleft.set(-0.3);
	   gripright.set(0.3);
   }
   
   /**
    * Sets gripper motor speeds to 0
    */
   public void stop() {
   	
	   gripleft.set(0);
	   gripright.set(0);
   }
    
    public void initDefaultCommand() {
    }
}

