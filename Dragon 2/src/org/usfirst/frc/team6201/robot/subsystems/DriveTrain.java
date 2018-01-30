package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public DifferentialDrive drive;
	
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(2);
    SpeedControllerGroup leftDrive = new SpeedControllerGroup(frontLeft, backLeft);
    
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(4);
    SpeedControllerGroup rightDrive = new SpeedControllerGroup(frontRight, backRight);
    
    private Joystick joystick;
    
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
    public DriveTrain() {
    	
    	backLeft = new WPI_TalonSRX(RobotMap.LEFT_DRIVE1);
    	frontLeft = new WPI_TalonSRX(RobotMap.LEFT_DRIVE2);
    	frontRight = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE1);
    	backRight = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE2);
    	
    	backLeft.setInverted(true);
    	frontLeft.setInverted(true);
    	
    	frontLeft.setNeutralMode(NeutralMode.Brake);
    	backLeft.setNeutralMode(NeutralMode.Brake);
    	frontRight.setNeutralMode(NeutralMode.Brake);
    	
    	
    	drive = new DifferentialDrive(leftDrive, rightDrive);
    	joystick = new Joystick(1);
    	
    }
	
    public void driveLR(double leftPower, double rightPower) {
    	
    	double joystickX = joystick.getX();
    	double joystickY = joystick.getZ();
    	
    	joystickX = joystickX * ArcadeDriveCmd.leftTalons;
    	joystickY = joystickY * ArcadeDriveCmd.rightTalons;
    	
    	drive.tankDrive(joystickX, joystickY);
    	
    }
    
    public void initDefaultCommand() {

    	setDefaultCommand(new ArcadeDriveCmd());
    
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

