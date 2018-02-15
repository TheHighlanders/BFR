package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTopScaleCmd extends Command {

	private boolean lessThanDistance() {
		
		return Robot.el.getEncoderDistance() < desiredDistance;
		
	}
	
	double desiredDistance = 78.0;
	
    public ElevatorTopScaleCmd() {
<<<<<<< HEAD
       
=======

>>>>>>> MaxTesting
    	requires(Robot.el);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	    	
    	Robot.el.encoder.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
<<<<<<< HEAD
    	if(Robot.el.getEncoderDistance() == desiredDistance) {
    		
    		end();
    		
    	} else if(lessThanDistance()) {
    			
    			Robot.el.ascend();
    		
    	}
=======
    	Robot.el.ascend();
>>>>>>> MaxTesting
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
<<<<<<< HEAD
        return Robot.el.getEncoderDistance() == desiredDistance;
=======
        return Robot.el.getEncoderRevs() >= 13;
>>>>>>> MaxTesting
        
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
