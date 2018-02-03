/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.ElevatorAscendCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorDescendCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	
	public double getRotationAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_ROTATE_AXIS);
		
	}
	
	public double getXAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_X_AXIS);
		
	}
	
	public double getYAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_Y_AXIS);
		
	}

	/**
	 * @return a double corresponding to the slider on the joystick roughly under 
	 * the wrist of someone if they are holding it.Has a range of -1 or 1, where 
	 * -1 is pointing the slider up and 1 is pointing it down
	 */
	public double getSliderAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_SLIDER_AXIS);
		
	}
	
	private Button b1 = new JoystickButton(logitech, 1);
	
	public boolean getButton1() {
		
		return b1.get();
		
	}
	
	public OI() {
		
		Button b9 = new JoystickButton(logitech, 9);
		b9.toggleWhenPressed(new ElevatorAscendCmd());
		
		Button b10 = new JoystickButton(logitech, 10);
		b10.toggleWhenPressed(new ElevatorDescendCmd());
		
		
	}
	
	

}
