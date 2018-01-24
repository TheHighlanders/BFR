/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6201.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDriveCmd extends Command {
	
	private double tanTurn;
	private double tanPower;
	
	private double processedTurn;
	private double processedPower;
	
	private final double TANDOMAIN_Y = 1.3;
	private final double TANDOMAIN_X = 1.3;
	
	private double scaledValTan(double rawVal, double domain) {
		
		return Math.tan((rawVal * domain) / (Math.tan(domain)));
		
	}
	
	public ArcadeDriveCmd() {

		requires(Robot.dt);

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		double joystickY = Robot.oi.getYAxisOfArcade();
		double joystickX = Robot.oi.getXAxisOfArcade();
		double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
		
		if(Robot.oi.getButton1()) {
			
			tanTurn = scaledValTan(joystickY, TANDOMAIN_X);
			tanPower = scaledValTan(joystickX, TANDOMAIN_Y);
			
			processedPower = tanPower * SmartDashboard.getNumber("TurboSpeed", 95);
			Robot.dt.turboBoostEnable();
			
		} else {
			
			tanTurn = scaledValTan(joystickY * joystickSlider, TANDOMAIN_X);
			tanPower = scaledValTan(joystickX * joystickSlider, TANDOMAIN_Y);
			
			processedPower = tanPower * 90;
			Robot.dt.turboBoostDisbale();
			
		}
		
		processedTurn = (1 - Math.abs(processedPower)) * tanTurn;
		
		Robot.dt.driveLR(-(processedPower - processedTurn), -(processedPower + processedTurn));
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.dt.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		this.end();
	}
}
