/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
	private DifferentialDrive m_myRobot;
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(2);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
	
	private Joystick joystick;

	@Override
	public void robotInit() {
		m_myRobot = new DifferentialDrive(frontLeft, frontRight);
		joystick = new Joystick(1);
	}

	@Override
	public void teleopPeriodic() {
		m_myRobot.tankDrive(joystick.getY(), joystick.getY());
	}
}
