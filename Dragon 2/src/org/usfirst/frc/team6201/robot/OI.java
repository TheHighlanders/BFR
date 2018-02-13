/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.ElevatorAscendCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorDescendCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorLowScaleCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorMidScaleCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorTopScaleCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This provides the framework to connect the DriverStation to the Robot both for getting values from the joystick(s), 
 * and for starting commands when buttons are pressed.
 * 
 * 
 * @author Baxter Ellard
 */
public class OI {
	
	/**
	 * Create an object out of our logitech arcade joystick.
	 * This allows us to get the  current position of the joystick, and the state of all the buttons.
	 * Initialized with the USB devices plugged into the robot
	 */
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	
	private Button b1 = new JoystickButton(logitech, 1);
	private Button b7 = new JoystickButton(logitech, 7);
	private Button b9 = new JoystickButton(logitech, 9);
	private Button b11 = new JoystickButton(logitech, 11);
	private Button b12 = new JoystickButton(logitech, 12);
	private Button b10 = new JoystickButton(logitech, 10);
	
	
	/**
	 * Create an object out of our XBox controller
	 * This allows us to get the axis and state the buttons
	 * Initialized with the USB devices plugged into the robot
	 */
	private XboxController xbox = new XboxController(RobotMap.XBOX);
	
	private Button lb = new JoystickButton(xbox,5);
	private Button rb = new JoystickButton(xbox, 6);
	
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
	
	public boolean getButton10() {
		
		return b10.get();
		
	}
	
	public boolean getButton12() {
		
		return b12.get();
		
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
	
	//X Box buttons
	public boolean getButtonlb() {
		return lb.get();
	}
	
	public boolean getButtonrb() {
		return rb.get();
	}
	
	public OI() {
		
		// starts the process of ascending to maximum elevator height
		
		b10.whileHeld(new ElevatorAscendCmd());
		
		b9.whileHeld(new ElevatorDescendCmd());
		
		//xbox controller
		//brings Elevator to the height of the scale and the bottom
		lb.whenPressed(new ElevatorLowScaleCmd());
		
		rb.whenPressed(new ElevatorTopScaleCmd());
		
		
		//b7.whenPressed(new ElevatorTopScaleCmd());
		//b9.whenPressed(new ElevatorMidScaleCmd());
		//b11.whenPressed(new ElevatorLowScaleCmd());
		
		
		
	}
	
	

}
