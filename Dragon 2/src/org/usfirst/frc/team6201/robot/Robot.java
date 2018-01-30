/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	public static final DriveTrain dt = new DriveTrain();
	
	public static OI oi;
	
	/**
	 * 1 = rear left
	 * 2 = front right
	 * 3 = front left
	 * 4 = rear right
	 */
/*
	WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
	WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
	
	WPI_TalonSRX left2 = new WPI_TalonSRX(3);
	WPI_TalonSRX right2 = new WPI_TalonSRX(4);
	
	DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);
	
	Joystick joystick = new 
	
	
	
	*/
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();
		//dt.calibrateGyro();
		
		SmartDashboard.putNumber("TurboSpeed", 0.95);
		DriverStation.reportWarning("Robot Initiated", false);

	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		//DriverStation.reportWarning("Joystick X: " + oi.getXAxisOfArcade(), false);
		//DriverStation.reportWarning("Joystick Y: " + oi.getYAxisOfArcade(), false);
		
		Robot.dt.driveLR(0, 0);
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
