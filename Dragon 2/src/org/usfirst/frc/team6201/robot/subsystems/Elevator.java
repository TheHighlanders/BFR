package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
    private DigitalInput maxSwitch = new DigitalInput(0);
    private DigitalInput minSwitch = new DigitalInput(1);
    
    private Encoder elevatorEnc = new Encoder(8, 9, false, Encoder.EncodingType.k4X);
    
    private static final double WHEEL_DIAMETER = 2.5;
    private static final double PULSE_PER_REVOLUTION = 360;
    private static final double ENCODER_GEAR_RATIO = 1;
    private static final double GEAR_RATIO = 12 / 1;
    private static final double FUDGE_FACTOR = 1.0;
    
    private final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION / ENCODER_GEAR_RATIO / GEAR_RATIO * FUDGE_FACTOR;
    
    public int count = elevatorEnc.get();
    double distance = elevatorEnc.getDistance();
    double rate = elevatorEnc.getRate();
    boolean stopped = elevatorEnc.getStopped();
    boolean direction = elevatorEnc.getDirection();
    
    // Instantiates TalonSRX motor controllers at CAN ports
    // 7 and 8.
    private WPI_TalonSRX elevator1 = new WPI_TalonSRX(7);
    private WPI_TalonSRX elevator2 = new WPI_TalonSRX(8);
        
    /**
     * Constructor, sets up motors and limit switches.
     */
    public Elevator() {
    	
    	elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR1);
    	elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR2);
    	
    	maxSwitch = new DigitalInput(RobotMap.MAX_LIMIT_SWITCH);
    	minSwitch = new DigitalInput(RobotMap.MIN_LIMIT_SWITCH);
    	
    	elevatorEnc.setMinRate(10);
    	elevatorEnc.setDistancePerPulse(DISTANCE_PER_PULSE);
    	
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
    public boolean minSwitchTriggered() {
    	
    	return minSwitch.get();
    	
    }
    
    /**
     * @return the current count of rotations. May be reset by calling reset()
     */
    public int getEncoderCount() {
    	
    	return count;
    	
    }
    
    /**
     * @return the current distance reading from the counter.
     */
    public double getEncoderDistance() {
    	
    	return distance;
    	
    }
    
    public double getEncoderRate() {
    	
    	return rate;
    	
    }
    
    public boolean getEncoderStopped() {
    	
    	return stopped;
    	
    }
    
    public boolean getEncoderDirection() {
    	
    	return direction;
    	
    }
    
    public boolean 
    
	/**
	 * Extends the elevator at a speed of 0.75.
	 */
    public void ascend() {
    	
    	elevator1.set(0.75);
    	elevator2.set(0.75);
    	
    }
    
    /**
     * Retracts the elevator at a speed of -0.75.
     */
    public void descend() {
    	
    	elevator1.set(-0.75);
    	elevator2.set(-0.75);
    	
    }
    
    /**
     * Sets motor speeds to 0, stopping elevator ascension or descension.
     */
    public void stop() {
    	
    	elevator1.set(0.0);
    	elevator2.set(0.0);
    	
    }

	protected void initDefaultCommand() {
		
	}
        
}

