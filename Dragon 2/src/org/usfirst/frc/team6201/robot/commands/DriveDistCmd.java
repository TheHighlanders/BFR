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
	private double driveSpeed;
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
	private final double MAXSPEEDTHRESH = 8;
	
	private boolean needReInit = true;
	
	/**
	 * Constructor
	 * 
	 * @param targetRotation			Degrees to turn the robot (pos = clockwise, neg = counterclockwise)
	 * @param acceptedAngleOffset		The difference between desired and current positions of our robot at which point this command will stop running.
	 */
	public DriveDistCmd(double targetDist, double acceptedDistOffset) {
	
		requires(Robot.dt);
		
		this.targetDistance = targetDistance;
		this.acceptedDistanceOffset = acceptedDistanceOffset;
	
	}
	
	protected void initialize() {
		
		currentDistanceOffset = targetDistance - Robot.dt.getDistanceTraveled();
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
		
		if (currentDistanceOffset >= MAXSPEEDTHRESH){
			Robot.dt.driveLR(1,-1);
		}
		
		else if (currentDistanceOffset <= -MAXSPEEDTHRESH){
			Robot.dt.driveLR(1,1);
		}
	
		else { 
			driveSpeed = Math.pow(Math.abs(currentDistanceOffset), 0.8) / 50;
			DriverStation.reportWarning("turnSpeed: " + driveSpeed + "currentAngleOffset: " + currentDistanceOffset, false);
			Robot.dt.driveLR(driveSpeed,driveSpeed);
				
		}
		
	}

	protected boolean isFinished() {
		//DriverStation.reportWarning("First Condition value is " + (Math.abs(currentAngleOffset) < acceptedAngleOffset), false);
		//return ((Math.abs(currentAngleOffset) < acceptedAngleOffset) && (Math.abs(Robot.dt.getGyroRate()) <= 10));
		return true;
	}
	
	protected void end() {
		needReInit = true;
		DriverStation.reportWarning("yo", false);

		Robot.dt.stop();
	
	}
	
	protected void interrupted() {
	
		end();
		
	}
	
}
