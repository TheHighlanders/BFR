package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDescend extends Command {

    public ElevatorDescend() {
       
    	requires(Robot.el);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.el.initializeCounters();
    	Robot.el.descend();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    //minSwitchSet = 1 when the switch triggers (ie elevator fully lowers)
    protected boolean isFinished() {
    	
        return Robot.el.minSwitchSet();
        
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
