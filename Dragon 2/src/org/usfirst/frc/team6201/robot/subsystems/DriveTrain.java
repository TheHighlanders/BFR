package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private WPI_TalonSRX left1 = new WPI_TalonSRX(1);
	private WPI_TalonSRX left2 = new WPI_TalonSRX(2);
	private WPI_TalonSRX right1 = new WPI_TalonSRX(3);
	private WPI_TalonSRX right2 = new WPI_TalonSRX(4);
		
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static int forwardOrReverse = -1;
	
    public DriveTrain() {
    	    	
    	left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE1);
    	left2 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE2);
    	right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE1);
    	right2 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE2);
    	
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
    
    public void driveLR(double leftPower, double rightPower) {
    	
  //  	DriverStation.reportWarning("Left Power: " + leftPower, false);
  //  	DriverStation.reportWarning("Right Power: " + rightPower, false);
    	
    	if(forwardOrReverse == 1) {
    		
    		left1.set(-leftPower);
    		left2.set(-leftPower);
    		right1.set(rightPower);
    		right2.set(rightPower);
    		
    		
    	} else {
    		
    		left1.set(leftPower);
    		left2.set(leftPower);
    		right1.set(-rightPower);
    		right2.set(-rightPower);
    		
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
    
}

