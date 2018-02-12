package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeEjectCmd extends Command {

	public static boolean cubeEject = false;
	
    public CubeEjectCmd() {
       
    	requires(Robot.gi);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.gi.eject();
    	
    	cubeEject = true;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.gi.stop();
    	
    	cubeEject = false;
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.gi.stop();
    	
    }
}
