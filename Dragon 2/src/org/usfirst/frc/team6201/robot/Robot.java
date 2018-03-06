/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot;

import org.usfirst.frc.team6201.robot.commands.autoL.AutoLLLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoL.AutoLRLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoL.AutoRLRCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoL.AutoRRRCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoR.RAutoLLLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoR.RAutoLRLCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoR.RAutoRLRCmdGroup;
import org.usfirst.frc.team6201.robot.commands.autoR.RAutoRRRCmdGroup;
import org.usfirst.frc.team6201.robot.subsystems.Climber;
import org.usfirst.frc.team6201.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6201.robot.subsystems.Elevator;
import org.usfirst.frc.team6201.robot.subsystems.GripperIntake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public static final Climber cl = new Climber();
	
	public char startingPos = 'D';
	
	Command autonomousCommand;
	SendableChooser autoChooser;
	
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
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Nothing", 'D');
		autoChooser.addObject("Starting on the left", 'L');
		autoChooser.addObject("Starting on the right", 'R');
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);

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
		//gameData = SmartDashboard.getString("Auto", "D");
		startingPos = (char) autoChooser.getSelected();
		// Checks for every possible arrangement
		// Put desired command group in each case
		//DriverStation.reportWarning("GameData is :" + gameData, false);
		if(gameData.length() > 0) {
			
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				DriverStation.reportWarning("About to choose LLL", false);
				if(startingPos == 'L'){
					autonomousCommand = new AutoLLLCmdGroup();
				}
				else if(startingPos == 'R'){
					autonomousCommand = new RAutoLLLCmdGroup();
				}
				
			} else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				if(startingPos == 'L'){
					autonomousCommand = new AutoLRLCmdGroup();
				}
				else if(startingPos == 'R'){
					autonomousCommand = new RAutoLRLCmdGroup();
				}
				
			} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				if(startingPos == 'L'){
					autonomousCommand = new AutoRLRCmdGroup();
				}
				else if(startingPos == 'R'){
					autonomousCommand = new RAutoRLRCmdGroup();
				}
				
			} else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
				if(startingPos == 'L'){
					autonomousCommand = new AutoRRRCmdGroup();
				}
				else if(startingPos == 'R'){
					autonomousCommand = new RAutoRRRCmdGroup();
				}
				
			} 

			
		}

		if (autonomousCommand != null){
			DriverStation.reportWarning("About to start", false);
			autonomousCommand.start();
		}
		
		dt.resetGyro();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
	}
	
	/**
	 * Runs before teleop begins
	 */
	
	@Override
	public void teleopInit() {
		DriverStation.reportWarning("Teleop Init!", false);
		Scheduler.getInstance().run();
		el.encoder.reset();
		el.height = 0;
 		dt.resetGyro();
 		dt.setEncoders(0);
 		gi.stop();
		dt.stop();
		autonomousCommand = null;
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		
		//DriverStation.reportWarning("DIO Port 1: " + el.magEnc.get(), false);
		//DriverStation.reportWarning("Min Switch: " + el.minSwitchTriggered(), false);
		//DriverStation.reportWarning("Encoder Revs: " + el.getEncoderRevs(), false);
		//DriverStation.reportWarning("Encoder Distance: " + el.getEncoderDistance(), false);
		//DriverStation.reportWarning("Encoder Stopped: " + el.getEncoderStopped(), false);
		//DriverStation.reportWarning("Ultrasonic Distance: " + gi.getUltrasonicDistance(), false);
		//DriverStation.reportWarning("Left Drive Encoder: " + -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition()), false);
		//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
		//DriverStation.reportWarning("Distance Traveled:" + Robot.dt.getDistanceTraveled(), false);
 		//DriverStation.reportWarning("Gyro Angle: " + dt.getGyroAngle(), false);
		//DriverStation.reportWarning("Elevator Height:" + Robot.el.height, false);
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void disabledInit() {
		
		dt.setEncoders(0);
		
		//DriverStation.reportWarning("DIO Port 1: " + el.magEnc.get(), false);
		//DriverStation.reportWarning("Max Switch: " + el.maxSwitchTriggered(), false);
		//DriverStation.reportWarning("Encoder Revs: " + el.getEncoderRevs(), false);
		//DriverStation.reportWarning("Encoder Distance: " + el.getEncoderDistance(), false);
		//DriverStation.reportWarning("Encoder Stopped: " + el.getEncoderStopped(), false);
		//DriverStation.reportWarning("Ultrasonic Distance: " + gi.getUltrasonicDistance(), false);
		//DriverStation.reportWarning("Left Drive Encoder: " + -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition()), false);
		//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
		//DriverStation.reportWarning("Distance Traveled:" + Robot.dt.getDistanceTraveled(), false);
 		//DriverStation.reportWarning("Gyro Angle: " + dt.getGyroAngle(), false);
		
	}
}