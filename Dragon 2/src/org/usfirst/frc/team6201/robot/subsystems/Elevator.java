package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
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
    
    public Counter encoder = new Counter(magEnc);
    
    private static final double WHEEL_DIAMETER = 2.5;
    private static final double PULSE_PER_REVOLUTION = 1;
    private static final double ENCODER_GEAR_RATIO = 1;
    private static final double GEAR_RATIO = 12 / 1;
    private static final double FUDGE_FACTOR = 1.0;
    
    private final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION / ENCODER_GEAR_RATIO / GEAR_RATIO * FUDGE_FACTOR;
    
    // Instantiates VictorSP motor controllers at PWM ports
    // 1 and 2.
    private VictorSP elevator1 = new VictorSP(RobotMap.ELEVATOR_MOTOR1);
    private VictorSP elevator2 = new VictorSP(RobotMap.ELEVATOR_MOTOR2);
          
    /**
     * Constructor, sets up motors and limit switches.
     */
    public Elevator() {
    	
    	//elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR1);
    	//elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR2);
    	

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
    
    /**
     * @return the current count of rotations. May be reset by calling reset()
     */
    public int getEncoderRevs() {
    	
    	return encoder.get();
    	
    }
    
    public double getEncoderDistance() {
    	
    	return encoder.getDistance();
    	
    }
    
    public double getEncoderRate() {
    	
    	return encoder.getRate();
    	
    }
    
    public boolean getEncoderStopped() {
    	
    	return encoder.getStopped();
    	
    }
    
	/**
	 * Extends the elevator at a speed of 0.75.
	 */
    public void ascend() {
    	
    	elevator1.set(1);
    	elevator2.set(-1);
    	
    }
    
    /**
     * Retracts the elevator at a speed of -0.75.
     */
    public void descend() {
    	
    	elevator1.set(-1);
    	elevator2.set(1);
    	
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

