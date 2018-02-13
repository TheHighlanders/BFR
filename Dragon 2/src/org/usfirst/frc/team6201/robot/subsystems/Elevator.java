package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Interface between the robot code and the motors and sensors involved
 * in making the elevator ascend and descend when we want it to. Includes
 * two TalonSRX motor controllers and two limit switches for detecting
 * maximum and minimum height.
 * 
 * TODO: Add encoders to detect how high the elevator is at a certain point.
 * 
 * @author Baxter Ellard
 */
public class Elevator extends Subsystem {
	
	// Instantiates limit switches at DIO ports 0 and 1.
    public DigitalInput maxSwitch = new DigitalInput(0);
    //private DigitalInput minSwitch = new DigitalInput(1);
    
    public DigitalInput magEnc = new DigitalInput(1);
    
    
    // Instantiates TalonSRX motor controllers at CAN ports
    // 7 and 8.
    private WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR1);
    private WPI_TalonSRX elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR2);
    /**
     * Constructor, sets up motors and limit switches.
     */
    public Elevator() {
    	
    	//elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR1);
    	//elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR2);
    	
    }
    
    public boolean magEncTriggered() {
    	
    	return !magEnc.get();
    	
    }
    /**
     * Checks the status of the limit switch wired to detect maximum elevator
     * height.
     * 
     * @return false if the switch has been activated. Use the opposite value
     * 		   of this when using the method in the isFinished() methods of
     *         commands. 
     */
    public boolean maxSwitchTriggered() {
    	
    	return maxSwitch.get();
    	
    }
    
    /**
     * Checks the status of the limit switch wired to detect minimum elevator
     * height.
     * 
     * @return false if the switch has been activated. Use the opposite value
     * 		   of this when using the method in the isFinished() methods of
     *         commands. 
     */
    /*public boolean minSwitchTriggered() {
    	
    	return minSwitch.get();
    	
    }*/
    
    public void constantForce() {
    	
    	elevator1.set(-0.1);
    	elevator2.set(0.1);
    	
    }
    
    /**
     * @return the current count of rotations. May be reset by calling reset()
     */
    
    
	/**
	 * Extends the elevator at a speed of 0.75.
	 */
    public void ascend() {
    	
    	elevator1.set(-1);
    	elevator2.set(1);
    	
    }
    
    /**
     * Retracts the elevator at a speed of -0.75.
     */
    public void descend() {
    	
    	elevator1.set(0.3);
    	elevator2.set(-0.3);
    }
    
    /**
     * Sets motor speeds to 0, stopping elevator ascension or descension.
     */
    public void stop() {
    	
    	elevator1.set(0);
    	elevator2.set(0);
    }

	protected void initDefaultCommand() {
		
	}
        
}

