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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {

	private TalonSRX left1;
	private TalonSRX left2;
	private TalonSRX right1;
	private TalonSRX right2;
	
	public static int forwardOrReverse = -1;
	
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public DriveTrain() {
		
		left1 = new TalonSRX(RobotMap.LEFT_DRIVE1);
		left2 = new TalonSRX(RobotMap.LEFT_DRIVE2);
		right1 = new TalonSRX(RobotMap.RIGHT_DRIVE1);
		right2 = new TalonSRX(RobotMap.RIGHT_DRIVE2);
		
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
		
		if(forwardOrReverse == 1) {
			
			left1.set(ControlMode.Velocity, leftPower);
			left2.set(ControlMode.Velocity, leftPower);
			right1.set(ControlMode.Velocity, rightPower);
			right2.set(ControlMode.Velocity, rightPower);
			
		} else {
			
			left1.set(ControlMode.Velocity, -leftPower);
			left2.set(ControlMode.Velocity, -leftPower);
			right1.set(ControlMode.Velocity, -rightPower);
			right2.set(ControlMode.Velocity, -rightPower);
			
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
	
	public void turboBoostDisbale() {
		
		left1.configOpenloopRamp(1/3, 0);
		left2.configOpenloopRamp(1/3, 0);
		right1.configOpenloopRamp(1/3, 0);
		right2.configOpenloopRamp(1/3, 0);
		
	}
	
}
