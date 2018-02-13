package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorAscendCmd extends Command {


    public ElevatorAscendCmd() {
       
    	requires(Robot.el);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.el.encoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.el.maxSwitchTriggered()){
    		Robot.el.ascend();
    	}
    	else{
    		Robot.el.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return false;
    
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
