package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistCmd extends Command {
 	
	/**
	 * driveSpeed is the speed that the robot drives at when it is going straight 
	 */
	private double driveSpeed;
	/**
	 * The speed of the wheels that you want to be slower when turning while moving forward.
	 * Set to left side if turning right, set to right side if turning left.
	 */
	private double turnSpeed = 0.4;
	/**
	 * targetDistance is how far the robot should travel
	 */
	private double targetDistance;
	/**
	 *  acceptedDistanceOffset is the acceptable amount of distance from the goal
	 */
	private double acceptedDistanceOffset;
	/**
	 * currentDistanceOffset the difference between the goal and location of the robot
	 */
	private double currentDistanceOffset;
	/**
	 * If the Robot is further from the goal than MAXSPEEDTHRESH, it moves full speed 
	 */
	private final double MAXSPEEDTHRESH = 24;
	/**
	 * Current gyro angle. Used for self correcting our angle as we move forwards to make sure we're going straight.
	 */
	private double currentAngle = Robot.dt.getGyroAngle();
	/**
	 * How far our current angle is away from 0.
	 */
	
	private boolean needReInit = true;
	
	/**
	 * Constructor
	 * 
	 * @param targetDist			Degrees to turn the robot (pos = clockwise, neg = counterclockwise)
	 * @param acceptedDistOffset	The allowable error between goal and final position of the Robot
	 */
	public DriveDistCmd(double targetDist, double acceptedDistOffset, double speed) {
	
		requires(Robot.dt);
		
		this.targetDistance = targetDist;
		this.acceptedDistanceOffset = acceptedDistOffset;
		this.driveSpeed = speed;
	
	}
	
	protected void initialize() {
		
		currentDistanceOffset = targetDistance;
		Robot.dt.setEncoders(0);
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
		DriverStation.reportWarning("Gyro Angle: " + Robot.dt.getGyroAngle(), false);
		currentAngle = Robot.dt.getGyroAngle();
		if (currentDistanceOffset >= MAXSPEEDTHRESH){
			if(currentAngle < -2.5) {
	    		DriverStation.reportWarning("a little to the left", false);
	    		Robot.dt.driveLR(driveSpeed, turnSpeed);
	    		
	    	} else if(currentAngle > 2.5) {
	    		DriverStation.reportWarning("a little to the right", false);
	    		Robot.dt.driveLR(turnSpeed, driveSpeed);
	    		
	    	} else {
	    		//DriverStation.reportWarning("juuuust right", false);
				Robot.dt.driveLR(driveSpeed, driveSpeed);
	    		
	    	}
		}
		
		else if (currentDistanceOffset <= -MAXSPEEDTHRESH){
			DriverStation.reportWarning("Negative distance", false);
			if(currentAngle > 2.5) {
	    		
	    		Robot.dt.driveLR(-driveSpeed, -turnSpeed);
	    		
	    	} else if(currentAngle < -2.5) {
	    		
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
