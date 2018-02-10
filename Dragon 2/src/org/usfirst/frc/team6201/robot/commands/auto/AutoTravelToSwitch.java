package org.usfirst.frc.team6201.robot.commands.auto;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTravelToSwitch extends Command {

	private int left1Pos = Robot.dt.left1.getSensorCollection().getPulseWidthPosition();
	private int left2Pos = Robot.dt.left2.getSensorCollection().getPulseWidthPosition();
	private int right1Pos = Robot.dt.right1.getSensorCollection().getPulseWidthPosition();
	private int right2Pos = Robot.dt.right2.getSensorCollection().getPulseWidthPosition();
	
	private boolean finished() {
		
		if(left1Pos == -36864 && left2Pos == -36864 && right1Pos == 36864 && right2Pos == 36864) {
			
			return true;
		
		} else {
			
			return false;
			
		}
		
	}
	
    public AutoTravelToSwitch() {
        
    	requires(Robot.dt);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.dt.left1.configReverseSoftLimitThreshold(-9 * 4096, 10);
    	Robot.dt.left2.configReverseSoftLimitThreshold(-9 * 4096, 10);
    	Robot.dt.right1.configForwardSoftLimitThreshold(9 * 4096, 10);
    	Robot.dt.right2.configForwardSoftLimitThreshold(9 * 4096, 10);
    	
    	Robot.dt.left1.configReverseSoftLimitEnable(true, 10);
    	Robot.dt.left2.configReverseSoftLimitEnable(true, 10);
    	Robot.dt.right1.configForwardSoftLimitEnable(true, 10);
    	Robot.dt.right2.configForwardSoftLimitEnable(true, 10);
    	
    	Robot.dt.driveLR(1, 1);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return finished();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.dt.left1.configReverseSoftLimitEnable(false, 10);
    	Robot.dt.left2.configReverseSoftLimitEnable(false, 10);
    	Robot.dt.right1.configForwardSoftLimitEnable(false, 10);
    	Robot.dt.right2.configForwardSoftLimitEnable(false, 10);
    	
    	Robot.dt.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
