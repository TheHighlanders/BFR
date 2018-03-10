package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.OI;
import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GripperPushCmd extends Command {


    public GripperPushCmd() {
       
    	requires(Robot.gi);
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    		Robot.gi.push();
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return !Robot.oi.joystickButtons[4].get();
    
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.gi.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }

}
