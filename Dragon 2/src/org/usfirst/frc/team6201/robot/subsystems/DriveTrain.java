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
public class DriveTrain extends Subsystem {

	public DifferentialDrive drive;
	
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(4);
    SpeedControllerGroup leftDrive = new SpeedControllerGroup(frontLeft, backLeft);
    
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(2);
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
    	    	
    	frontLeft.setNeutralMode(NeutralMode.Brake);
    	backLeft.setNeutralMode(NeutralMode.Brake);
    	frontRight.setNeutralMode(NeutralMode.Brake);
    	backRight.setNeutralMode(NeutralMode.Brake);
    	
    	drive = new DifferentialDrive(leftDrive, rightDrive);
    	joystick = new Joystick(1);
    	
    }
	
    public void driveLR(double leftPower, double rightPower) {
    	
    	double joystickForward = joystick.getY();
    	double joystickTurn = 1.0 * joystick.getY();
    	double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
/**    	
    	tanPower = scaledValTan(joystickForward * joystickSlider, TANDOMAIN_X);
    	tanTurn = scaledValTan(joystickTurn * joystickSlider, TANDOMAIN_Y);
    	
    	processedPower = tanPower;
    	processedTurn = (1 - Math.abs(processedPower)) * tanTurn;
**/    	
    	
    	if(Math.abs(joystickForward) < turnSens) {
    		
    		joystickForward = 0;
    		
    	}
    	
    	if(Math.abs(joystickTurn) < turnSens) {
    		
    		joystickTurn = 0;
    		
    	}    	

    	
//    	joystickForward = -(processedPower - processedTurn);
//   	joystickTurn = -(processedPower + processedTurn);

    	joystickForward = joystickForward * joystickSlider;
    	joystickTurn = joystickTurn * joystickSlider;
    	
    	DriverStation.reportWarning("Left Power: " + joystickForward, false);
    	DriverStation.reportWarning("Right Power: " + joystickTurn, false);
    	
    	drive.arcadeDrive(joystickForward, joystickTurn);
    	
    }
    
    public void initDefaultCommand() {

    	
    
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

