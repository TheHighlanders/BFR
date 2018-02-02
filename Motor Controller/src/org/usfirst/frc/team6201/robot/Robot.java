/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Spark;

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and speed controller inputs also
 * range from -1 to 1 making it easy to work together.
 */
public class Robot extends IterativeRobot {
	private static final int motor1 = 0;
	private static final int motor2 = 1;
	private static final int kJoystickPort = 1;

	private SpeedController motor_1;
	private SpeedController motor_2;
	private Joystick m_joystick;

	@Override
	public void robotInit() {
		motor_1 = new Spark(motor1);
		motor_2 = new Spark(motor2);
		m_joystick = new Joystick(kJoystickPort);
	}

	@Override
	public void teleopPeriodic() {
		motor_1.set(m_joystick.getY());
		motor_2.set(m_joystick.getY());
	}
}
