package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCmd extends Command {

	/**
	 * The distance in inches that we want to travel.
	 */
	private double targetDistance;
	/**
	 * Travel distances will not be exact, so this is the range in which we say "close enough" and stop the command.
	 */
	private double acceptedDistOffset;
	/**
	 * Our current distance from the target distance.
	 */
	private double currentDistOffset;
	/**
	 * Current gyro angle. Used for self correcting our angle as we move forwards to make sure we're going straight.
	 */
	private double currentAngle = Robot.dt.getGyroAngle();
	/**
	 * How far our angle is away from 0.
	 */
	private double currentAngleOffset = 0 - currentAngle;
	
	private boolean needReInit = true;
	
	/**
	 * Get the left encoder's reading because right encoder always starts at 3 for some reason.
	 * Make it negative to invert it and make the readings from both encoders match.
	 */
	private double revs = -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition() / 4096);
	
	/**
	 * Used to find how far we have traveled by taking the distance we travel in one wheel rotation
	 * multiplying it by the number of rotations.
	 * 
	 * @param the number of rotations the encoder has read
	 * @return the rotations multiplied by the circumference of the wheel (about 19 inches).
	 */
	private double rotationsToInches(double rotations) {
		
		return rotations * 19;
		
	}
	
    public DriveDistanceCmd(double targetDistance, double acceptedDistOffset) {
       
    	requires(Robot.dt);
    	
    	this.targetDistance = targetDistance;
    	this.acceptedDistOffset = acceptedDistOffset;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	// Resets encoders to 0
    	Robot.dt.left1.getSensorCollection().setPulseWidthPosition(0, 0);
    	Robot.dt.right1.getSensorCollection().setPulseWidthPosition(0, 0);
    	
    	needReInit = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(needReInit) {
    		
    		initialize();
    		
    	}
    	
    	// These if else statements check if the robot is angled to the right or left more than 
    	// 10 degrees and automatically adjusts our angle so we stay on course.
    	if(currentAngleOffset > 10) {
    		
    		Robot.dt.driveLR(-0.3, 0.3);
    		
    	} else if(currentAngleOffset < -10) {
    		
    		Robot.dt.driveLR(0.3, -0.3);
    		
    	} else {
    		
    		Robot.dt.driveLR(0.3, 0.3);
    		
    	}
    	
    	currentDistOffset = targetDistance - rotationsToInches(revs);
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	return (Math.abs(currentDistOffset) < acceptedDistOffset);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	needReInit = true;
    	
    	Robot.dt.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
