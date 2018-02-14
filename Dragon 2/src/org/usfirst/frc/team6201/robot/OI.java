/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.ElevatorAscendCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorDescendCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorMidScaleCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorTopScaleCmd;
import org.usfirst.frc.team6201.robot.commands.GripperPullCmd;
import org.usfirst.frc.team6201.robot.commands.GripperPushCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This provides the framework to connect the DriverStation to the Robot both for getting values from the joystick(s), 
 * and for starting commands when buttons are pressed.
 * 
 * 
 * @author Baxter Ellard
 * @author David Matthews
 */
public class OI {
	
	/**
	 * Create an object out of our logitech arcade joystick.
	 * This allows us to get the  current position of the joystick, and the state of all the buttons.
	 * Initialized with the USB devices plugged into the robot
	 */
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	
	private Button b1 = new JoystickButton(logitech, 1);
	
	Button b7 = new JoystickButton(logitech, 7);
	Button b9 = new JoystickButton(logitech, 9);
	Button b3 = new JoystickButton(logitech, 3);
	Button b4 = new JoystickButton(logitech, 4);
	Button b11 = new JoystickButton(logitech, 11);
	Button b12 = new JoystickButton(logitech, 12);
	
	/**
	 * @return  a double corresponding to how much the joystick's handle is rotated.
	 * This has a range of -1 to 1. All the way to the right is +1.
	 */
	public double getRotationAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_ROTATE_AXIS);
		
	}
	
	/**
	 * @return a double corresponding to the position of the joystick in the side to side direction (X axis).
	 * Range of -1 to 1. All the way to the right is +1.
	 */
	public double getXAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_X_AXIS);
		
	}
	
	/**
	 * @return a double corresponding to the position of the joystick in the Y axis (front and back).
	 * range of -1 to 1, with all the way forward being 1
	 */
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
	
	/**
	 * @return True if button 1 is pressed, false otherwise.
	 */
	public boolean getButton1() {
		
		return b1.get();
		
	}
	
	public boolean getButton7() {
		
		return b7.get();
		
	}
	
	public boolean getButton9() {
		
		return b9.get();
		
	}
	
	public boolean getButton11() {
		
		return b11.get();
		
	}
	
	public boolean getButton12() {
		
		return b12.get();
		
	}
	
	public OI() {
		
		// starts the process of ascending to maximum elevator height
		b7.whileHeld(new ElevatorAscendCmd());
		
		b9.whileHeld(new ElevatorDescendCmd());
		
		b3.whileHeld(new GripperPullCmd());
		
		b4.whileHeld(new GripperPushCmd());
		
		b11.whileHeld(new ElevatorTopScaleCmd());
		b12.whileHeld(new ElevatorMidScaleCmd());
		
		//Button b10 = new JoystickButton(logitech, 10);
		//b10.toggleWhenPressed(new ElevatorDescendCmd());
		
		
	}

}
