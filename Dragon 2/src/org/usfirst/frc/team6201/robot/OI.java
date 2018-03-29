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
import org.usfirst.frc.team6201.robot.commands.ElevatorMaxCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorMidScaleCmd;
import org.usfirst.frc.team6201.robot.commands.GripperPullCmd;
import org.usfirst.frc.team6201.robot.commands.GripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.SmallLiftCmd;
import org.usfirst.frc.team6201.robot.commands.autoR.RAutoRLRCmdGroup;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
	
	public Button[] joystickButtons = new Button[13];
	public Button[] xboxButtons = new Button[7]; 
	
	private XboxController xbox = new XboxController(RobotMap.XBOX);
	
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
	
	public OI() {
		
		for(int i = 1; i < joystickButtons.length; i++) {
			
			joystickButtons[i] = new JoystickButton(logitech, i);
			
		}
		
		for(int i = 1; i < xboxButtons.length; i++) {
			
			xboxButtons[i] = new JoystickButton(xbox, i);
			
		}
		
		joystickButtons[2].whenPressed(new RAutoRLRCmdGroup());
		
		joystickButtons[7].whenPressed(new ElevatorMaxCmd());
		joystickButtons[9].whenPressed(new ElevatorMidScaleCmd());
		joystickButtons[11].whenPressed(new ElevatorLowScaleCmd());
		
		joystickButtons[5].whileHeld(new ElevatorAscendCmd());
		joystickButtons[6].whileHeld(new ElevatorDescendCmd());
		
		joystickButtons[3].whileHeld(new GripperPullCmd());
		joystickButtons[4].whileHeld(new GripperPushCmd());
		/*
		xboxButtons[1].whenPressed(new ElevatorLowScaleCmd());
		xboxButtons[2].whenPressed(new ElevatorMidScaleCmd());
		xboxButtons[3].whenPressed(new ElevatorBottomCmd());
		xboxButtons[4].whenPressed(new ElevatorMaxCmd());
		*/
		
		//xboxButtons[2].toggleWhenPressed(new ElevatorConstantForceCmd());
		xboxButtons[5].whileHeld(new ElevatorAscendCmd());
		xboxButtons[6].whileHeld(new ElevatorDescendCmd());
		
	}

}
