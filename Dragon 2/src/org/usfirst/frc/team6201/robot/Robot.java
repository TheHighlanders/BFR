/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6201.robot.subsystems.Elevator;
import org.usfirst.frc.team6201.robot.subsystems.GripperIntake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	/**
	 * Creates a DriveTrain subsystem object which enables moving the robot
	 * around.
	 */
	public static final DriveTrain dt = new DriveTrain();
		
	/**
	 * Creates a GripperIntake subsystem object which enables the automatic
	 * start and stop of gripper motors depending on a target's distance
	 * from the gripper.
	 */
	public static final GripperIntake gi = new GripperIntake();
	
	//public static final Elevator el = new Elevator();
	
	public static OI oi;
	
	/**
	 * 1 = rear left
	 * 2 = front left
	 * 3 = rear right
	 * 4 = front right
	 * 5 = gripper left
	 * 6 = gripper right
	 */
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = new OI();
		dt.calibrateGyro();
		
		//SmartDashboard.putNumber("TurboSpeed", 0.95);
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
		
		Scheduler.getInstance().run();
		
		DriverStation.reportWarning("Limit Switch Status: " + Robot.dt.limitSwitch.get(), false);
		
 		//gi.startWheels();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}