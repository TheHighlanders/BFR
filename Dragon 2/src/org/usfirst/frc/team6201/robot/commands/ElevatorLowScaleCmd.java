package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorLowScaleCmd extends Command {

	private boolean lessThanDistance() {
		
		return Robot.el.getEncoderDistance() < desiredDistance;
		
	}
	
	double desiredDistance = 66.0;
	
    public ElevatorLowScaleCmd() {
       
    	requires(Robot.el);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.el.getEncoderDistance() == desiredDistance) {
    		
    		end();
    		
    	} else if(lessThanDistance()) {
    			
    			Robot.el.ascend();
    		
    	} else if(!lessThanDistance()) {
    			
    			Robot.el.descend();
		
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return Robot.el.getEncoderDistance() == desiredDistance;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.el.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }

}
