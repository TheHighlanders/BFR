/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class FailedDriveTrain extends Subsystem {
	
	private WPI_TalonSRX left1 = new WPI_TalonSRX(1);
	private WPI_TalonSRX left2 = new WPI_TalonSRX(2);
	private WPI_TalonSRX right1 = new WPI_TalonSRX(3);
	private WPI_TalonSRX right2 = new WPI_TalonSRX(4);
	
	public static int forwardOrReverse = -1;
	
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public FailedDriveTrain() {
		
		DriverStation.reportWarning("", false);
		
		left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE1);
		left2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE2);
		right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE1);
		right2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE2);
		
		left1.setInverted(true);
		left2.setInverted(true);
		
		left1.configOpenloopRamp(1/3, 0);
		left2.configOpenloopRamp(1/3, 0);
		right1.configOpenloopRamp(1/3, 0);
		right2.configOpenloopRamp(1/3, 0);
		
		left1.setNeutralMode(NeutralMode.Brake);
		left2.setNeutralMode(NeutralMode.Brake);
		right1.setNeutralMode(NeutralMode.Brake);
		right2.setNeutralMode(NeutralMode.Brake);
		
	}
	
	public void initDefaultCommand() {

		setDefaultCommand(new ArcadeDriveCmd());
	
	}
	
	public void driveLR(double leftPower,double rightPower) {
		
		DriverStation.reportWarning("driveLR Reached.", false);
		DriverStation.reportWarning("Left Power: " + leftPower, false);
		DriverStation.reportWarning("Right Power: " + rightPower, false);
		
		if(forwardOrReverse == 1) {
			
			left1.set(ControlMode.PercentOutput, leftPower);
			left2.set(ControlMode.PercentOutput, leftPower);
			right1.set(ControlMode.PercentOutput, rightPower);
			right2.set(ControlMode.PercentOutput, rightPower);
			
		} else {
			
			left1.set(ControlMode.PercentOutput, -leftPower);
			left2.set(ControlMode.PercentOutput, -leftPower);
			right1.set(ControlMode.PercentOutput, -rightPower);
			right2.set(ControlMode.PercentOutput, -rightPower);
			
		}
		
	}
	
	public void stop() {
		
		this.driveLR(0, 0);
		
	}
	
	public void calibrateGyro() {
		
		gyro.calibrate();
		
	}
	
	public void resetGyro() {
		
		gyro.reset();
		
	}
	
	public void getGyroRate() {
		
		gyro.getRate();
		
	}
	
	public void turboBoostEnable() {
		
		left1.configOpenloopRamp(1/5, 0);
		left2.configOpenloopRamp(1/5, 0);
		right1.configOpenloopRamp(1/5, 0);
		right2.configOpenloopRamp(1/5, 0);
		
	}
	
	public void turboBoostDisable() {
		
		left1.configOpenloopRamp(1/3, 0);
		left2.configOpenloopRamp(1/3, 0);
		right1.configOpenloopRamp(1/3, 0);
		right2.configOpenloopRamp(1/3, 0);
		
	}
	
}
