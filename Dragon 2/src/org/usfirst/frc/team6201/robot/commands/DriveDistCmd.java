package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistCmd extends Command {
 	
	/**
	 * turnSpeed is the speed that the robot turns at depends on currentAngleOffset 
	 */
	private double driveSpeed = 0.5;
	/**
	 * The set of wheels you want to be slower when turning while moving forward.
	 * Set to left side if turning right, set to right side if turning left.
	 */
	private double turnSpeed = 0.3;
	/**
	 * targetRotation is how far we want to turn the robot from the inital conditions 
	 */
	private double targetDistance;
	/**
	 *  acceptedAngleOffset is the difference between desired and current positions of our robot at which point this command will stop running.
	 */
	private double acceptedDistanceOffset;
	/**
	 * currentAngleOffset the difference between targetRotion and current angle of the robot
	 */
	private double currentDistanceOffset;
	/**
	 * MAXSPEEDTHRESH is the angleOffSet where you rotate full speed  
	 */
	private final double MAXSPEEDTHRESH = 24;
	/**
	 * Current gyro angle. Used for self correcting our angle as we move forwards to make sure we're going straight.
	 */
	private double currentAngle = Robot.dt.getGyroAngle();
	/**
	 * How far our angle is away from 0.
	 */
	private double currentAngleOffset;
	
	private boolean needReInit = true;
	
	/**
	 * Constructor
	 * 
	 * @param targetRotation			Degrees to turn the robot (pos = clockwise, neg = counterclockwise)
	 * @param acceptedAngleOffset		The difference between desired and current positions of our robot at which point this command will stop running.
	 */
	public DriveDistCmd(double targetDist, double acceptedDistOffset) {
	
		requires(Robot.dt);
		
		this.targetDistance = targetDist;
		this.acceptedDistanceOffset = acceptedDistOffset;
	
	}
	
	protected void initialize() {
		
		currentDistanceOffset = targetDistance;
		Robot.dt.resetEncoders();
		Robot.dt.resetGyro();
		needReInit = false;
	
	}
	
	/**
	 * This method calculate the speed of the motor based off of currentAngleOffset
	 */
	protected void execute() {
		if (needReInit){
			initialize();
		}
		currentDistanceOffset = targetDistance - Robot.dt.getDistanceTraveled();
		currentAngleOffset = 0 - currentAngle;
		
		if (currentDistanceOffset >= MAXSPEEDTHRESH){
			
			if(currentAngleOffset > 2.5) {
	    		//DriverStation.reportWarning("a little to the left", false);
	    		Robot.dt.driveLR(driveSpeed, turnSpeed);
	    		
	    	} else if(currentAngleOffset < -2.5) {
	    		//DriverStation.reportWarning("a little to the right", false);
	    		Robot.dt.driveLR(turnSpeed, driveSpeed);
	    		
	    	} else {
	    		//DriverStation.reportWarning("juuuust right", false);
				Robot.dt.driveLR(driveSpeed, driveSpeed);
	    		
	    	}
		}
		
		else if (currentDistanceOffset <= -MAXSPEEDTHRESH){
			
			if(currentAngleOffset > 2.5) {
	    		
	    		Robot.dt.driveLR(-driveSpeed, -turnSpeed);
	    		
	    	} else if(currentAngleOffset < -2.5) {
	    		
	    		Robot.dt.driveLR(-turnSpeed, -driveSpeed);
	    		
	    	} else {
	    		
				Robot.dt.driveLR(-driveSpeed, -driveSpeed);
	    		
	    	}
				
		}
	
		else { 
			//driveSpeed = Math.pow(Math.abs(currentDistanceOffset), 0.8) / 50;
			//DriverStation.reportWarning("turnSpeed: " + driveSpeed + "currentDistanceOffset: " + currentDistanceOffset, false);
			Robot.dt.driveLR(driveSpeed/2, driveSpeed/2);
				
		}
		
	}

	protected boolean isFinished() {
		//DriverStation.reportWarning("First Condition value is " + (Math.abs(currentAngleOffset) < acceptedAngleOffset), false);
		return (Math.abs(currentDistanceOffset) < acceptedDistanceOffset);
		
	}
	
	protected void end() {
		needReInit = true;
		//DriverStation.reportWarning("yo", false);

		Robot.dt.stop();
	
	}
	
	protected void interrupted() {
	
		end();
		
	}
	
}
