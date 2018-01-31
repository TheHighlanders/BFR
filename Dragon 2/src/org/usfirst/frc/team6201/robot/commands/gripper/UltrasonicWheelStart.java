package org.usfirst.frc.team6201.robot.commands.gripper;

import org.usfirst.frc.team6201.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicWheelStart extends Command {

	private WPI_TalonSRX leftIntake = new WPI_TalonSRX(5);
	private WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
    public UltrasonicWheelStart() {

    	requires(Robot.us);
    	requires(Robot.dt);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
