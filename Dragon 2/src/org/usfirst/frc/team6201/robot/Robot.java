/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.auto.AutoLLLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.auto.AutoLRLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.auto.AutoRLRCmdGroup;
import org.usfirst.frc.team6201.robot.commands.auto.AutoRRRCmdGroup;
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
 * 
 * @author Baxter Ellard
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
	
	/**
	 * Creates an Elevator subsystem object which enables moving the elevator up and down.
	 */
	public static final Elevator el = new Elevator();
	
	/**
	 * Declare the Operator Interface object. DO NOT initialize it here; that
	 * would cause No Robot Code to occur.
	 */
	public static OI oi;
	
	/**
	 * TalonSRX CAN Port Assignments:
	 * 1 = rear left
	 * 2 = front left
	 * 3 = rear right
	 * 4 = front right
	 * 5 = gripper left
	 * 6 = gripper right
	 * 7 = elevator motor 1
	 * 8 = elevator motor 2
	 */
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = new OI();
		dt.calibrateGyro();
		
		Robot.dt.left1.getSensorCollection().setPulseWidthPosition(0, 0);
		Robot.dt.right1.getSensorCollection().setPulseWidthPosition(0, 0);
		
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
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		// Checks for every possible arrangement
		// Put desired command group in each case
		if(gameData.length() > 0) {
			
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				
				new AutoLLLCmdGroup();
				
			} else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				
				new AutoLRLCmdGroup();
				
			} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				
				new AutoRLRCmdGroup();
				
			} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
				
				new AutoRRRCmdGroup();
				
			} 

			
		}

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
		
		//DriverStation.reportWarning("DIO Port 1: " + el.magEnc.get(), false);
		//DriverStation.reportWarning("Max Switch: " + el.maxSwitchTriggered(), false);
		//DriverStation.reportWarning("Encoder Revs: " + el.getEncoderRevs(), false);
		DriverStation.reportWarning("Encoder Distance: " + el.getEncoderDistance(), false);
		//DriverStation.reportWarning("Encoder Stopped: " + el.getEncoderStopped(), false);
		//DriverStation.reportWarning("Ultrasonic Distance: " + gi.getUltrasonicDistance(), false);
		DriverStation.reportWarning("Left Drive Encoder: " + -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition() / 1024), false);
		DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition() / 1024, false);
		
 		//gi.startWheels();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}