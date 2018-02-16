package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCmd extends Command {

	private double targetDistance;
	private double acceptedDistOffset;
	private double currentDistOffset;
	
	private boolean needReInit = true;
	
	private double revs = -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition() / 1024);
	
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
    	
    	Robot.dt.left1.getSensorCollection().setPulseWidthPosition(0, 0);
    	Robot.dt.right1.getSensorCollection().setPulseWidthPosition(0, 0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	currentDistOffset = targetDistance - rotationsToInches(revs);
    	
    	Robot.dt.driveLR(0.9, 0.9);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	return (Math.abs(currentDistOffset) < acceptedDistOffset);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
