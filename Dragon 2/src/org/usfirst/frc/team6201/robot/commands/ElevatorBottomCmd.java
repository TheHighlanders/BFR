package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorBottomCmd extends Command {

    private int startingHeight;

	public ElevatorBottomCmd() {
       
    	requires(Robot.el);
    	startingHeight = Robot.el.height;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.el.encoder.reset();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.el.descend();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return Robot.el.minSwitchTriggered();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.el.height = 0;
    	Robot.el.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }
    
}