package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public abstract class DriveTrain extends Subsystem {
/*
	public DifferentialDrive drive;
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(2);
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
    SpeedControllerGroup leftDrive = new SpeedControllerGroup(frontLeft, backLeft);
    
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(4);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(3);
    SpeedControllerGroup rightDrive = new SpeedControllerGroup(frontRight, backRight);
    
    private Joystick joystick;

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
 
    private double turnSens = 0.10;
    
    private double tanPower;
    private double tanTurn;
    
    private double processedPower;
    private double processedTurn;
    
    private final double TANDOMAIN_Y = 1.3;
    private final double TANDOMAIN_X = 1.3;
    
    private double scaledValTan(double rawVal, double domain) {
    	
    	return Math.tan((rawVal * domain) / (Math.tan(domain)));
    	
    }
    
    public DriveTrain() {
    	
    	backLeft = new WPI_TalonSRX(RobotMap.LEFT_DRIVE1);
    	frontLeft = new WPI_TalonSRX(RobotMap.LEFT_DRIVE2);
    	frontRight = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE1);
    	backRight = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE2); 
    	    	
    	frontLeft.setInverted(true);
    	backLeft.setInverted(true);
    	    	
    	frontLeft.setNeutralMode(NeutralMode.Brake);
    	backLeft.setNeutralMode(NeutralMode.Brake);
    	frontRight.setNeutralMode(NeutralMode.Brake);
    	backRight.setNeutralMode(NeutralMode.Brake);
    	
    	drive = new DifferentialDrive(leftDrive, rightDrive);    	
    	joystick = new Joystick(1);
    	
    }
	
    public void driveLR() {
    	
    	double joystickPower = Robot.oi.getYAxisOfArcade();
    	double joystickTurn = Robot.oi.getYAxisOfArcade();
    	
    	double joystickY = joystick.getY();
    	
    	DriverStation.reportWarning("Arcade Y: " + Robot.oi.getYAxisOfArcade(), false);
    	DriverStation.reportWarning("Jostick Y: " + joystickY, false);
    	
    	if(joystickY == Robot.oi.getYAxisOfArcade()) {
    		
    		DriverStation.reportWarning("Y axes are equivalent. The problem you're looking for probably isn't here.", false);
    		
    	} else {
    		
    		DriverStation.reportWarning("Y axes are not equivalent. Maybe you should work on fixing that?", false);
    		
    	}
    	
    	double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
    	
    	tanPower = scaledValTan(joystickPower * joystickSlider, TANDOMAIN_X);
    	tanTurn = scaledValTan(joystickTurn * joystickSlider, TANDOMAIN_Y);
    	
    	processedPower = tanPower * 0.90;
    	processedTurn = (1 - Math.abs(processedPower)) * tanTurn;
    	    	
    	
    	if(Math.abs(joystickPower) < turnSens) {
    		
    		joystickPower = 0;
    		
    	}
    	
    	if(Math.abs(joystickTurn) < turnSens) {
    		
    		joystickTurn = 0;
    		
    	}    	

    	
    	joystickPower = -(processedPower - processedTurn);
    	joystickTurn = -(processedPower + processedTurn);
    	
    	//joystickPower = joystickLeft * joystickSlider;
    	//joystickTurn = joystickRight * joystickSlider;
    	
    	DriverStation.reportWarning("Power: " + joystickPower, false);
    	DriverStation.reportWarning("Turn: " + joystickTurn, false);
    	
    	
    	drive.arcadeDrive(joystickPower, joystickTurn);
    	
    }
  
    public void initDefaultCommand() {

    	
    
    }
    
    public void stop() {
    	
    	//this.driveLR(0, 0);
    	
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
    
   */ 
}