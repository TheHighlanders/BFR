package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ElevatorConstantForceCmd;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Interface between the robot code and the motors and sensors involved
 * in making the elevator ascend and descend when we want it to. Includes
 * two TalonSRX motor controllers and two limit switches for detecting
 * maximum and minimum height.
 * 
 * Elevator Heights:
 * Top - 78 inches
 * Mid - 66 inches
 * Low - 54 inches
 * Switch - 24 inches
 * 
 * TODO: Add encoders to detect how high the elevator is at a certain point.
 * 
 * @author Baxter Ellard
 */
public class Elevator extends Subsystem {
	
	// Instantiates limit switches at DIO ports 0 and 1.
    public DigitalInput maxSwitch = new DigitalInput(0);
    
    public DigitalInput magEnc = new DigitalInput(1);
    
    public Counter encoder = new Counter(magEnc);
    private DigitalInput minSwitch = new DigitalInput(2);
 
    private static final double WHEEL_DIAMETER = 4;/*
    private static final double PULSE_PER_REVOLUTION = 1;
    private static final double GEAR_RATIO = 12 / 1;
    private static final double FUDGE_FACTOR = 1.0;
    
    //Distance per pulse might just be circumference
    //Every twelfth of the circumference (pi/6 rad) is one rotation of the internal motor
    private final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION / GEAR_RATIO * FUDGE_FACTOR;
    */
    
    private final double DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER;
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
    	elevator1.configOpenloopRamp(1/2, 0);
    	elevator2.configOpenloopRamp(1/2, 0);
    	
    	//play around with this
    	//encoder.setMaxPeriod(0.1);
    	encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
    	
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
    	
    	return !maxSwitch.get();
    	
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
    	
    	return !minSwitch.get();
    	
    }
    
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

    	elevator1.set(-0.9);
    	elevator2.set(9);
    	
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
		
		setDefaultCommand(new ElevatorConstantForceCmd());
		
	}
        
}

