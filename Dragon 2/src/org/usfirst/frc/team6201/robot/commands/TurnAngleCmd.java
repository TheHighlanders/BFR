package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngleCmd extends Command {
	
	/**
	 * turnSpeed is the speed that the robot turns at depends on currentAngleOffset 
	 */
	private double turnSpeed;
	/**
	 * targetRotation is how far we want to turn the robot from the inital conditions 
	 */
	private double targetRotation;
	/**
	 *  acceptedAngleOffset is the difference between desired and current positions of our robot at which point this command will stop running.
	 */
	private double acceptedAngleOffset;
	/**
	 * currentAngleOffset the difference between targetRotion and current angle of the robot
	 */
	private double currentAngleOffset;
	/**
	 * MAXSPEEDTHRESH is the angleOffSet where you rotate full speed  
	 */
	private final double MAXSPEEDTHRESH = 84;
	
	private boolean needReInit = true;
	
	/**
	 * Constructor
	 * 
	 * @param targetRotation			Degrees to turn the robot (pos = clockwise, neg = counterclockwise)
	 * @param acceptedAngleOffset		The difference between desi
	 * red and current positions of our robot at which point this command will stop running.
	 */
	public TurnAngleCmd(double targetRotation, double acceptedAngleOffset) {
	
		requires(Robot.dt);
		
		this.targetRotation = targetRotation;
		this.acceptedAngleOffset = acceptedAngleOffset;
	
	}
	
	protected void initialize() {
		
		Robot.dt.resetGyro();
		currentAngleOffset = targetRotation - Robot.dt.getGyroAngle();
		needReInit = false;
	
	}
	
	/**
	 * This method calculate the speed of the motor based off of currentAngleOffset
	 */
	protected void execute() {
		if (needReInit){
			initialize();
		}
		currentAngleOffset = targetRotation - Robot.dt.getGyroAngle();
		
		if (currentAngleOffset >= MAXSPEEDTHRESH){
			Robot.dt.driveLR(0.9,-0.9);
		}
		
		else if (currentAngleOffset <= -MAXSPEEDTHRESH){
			Robot.dt.driveLR(-0.9,0.9);
		}
	
		else { 
			turnSpeed = Math.pow(Math.abs(currentAngleOffset), 0.8) / 50;
			DriverStation.reportWarning("turnSpeed: " +turnSpeed + "currentAngleOffset: " +currentAngleOffset, false);
			Robot.dt.driveLR(turnSpeed,-turnSpeed);
				
		}
		
	}

	protected boolean isFinished() {
		//DriverStation.reportWarning("First Condition value is " + (Math.abs(currentAngleOffset) < acceptedAngleOffset), false);
		return ((Math.abs(currentAngleOffset) < acceptedAngleOffset) && (Math.abs(Robot.dt.getGyroRate()) <= 10));
		
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
