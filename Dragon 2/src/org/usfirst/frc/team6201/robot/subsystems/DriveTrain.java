package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
* The interface between the robot code and the actuators and sensors involved
* in moving the robot. Right now this is just the motors and gyro, but this
* will probably grow to include encoders.
* 
* TODO: Add a function that slows down the robot based on how high the elevator
* is.
* 
* @author Baxter Ellard
* @author David Matthews
*/

public class DriveTrain extends Subsystem {

	// Instantiating TalonSRX motor controllers at CAN ports
	// 1 through 4.
	public WPI_TalonSRX left1 = new WPI_TalonSRX(1);
	private WPI_TalonSRX left2 = new WPI_TalonSRX(2);
	public WPI_TalonSRX right1 = new WPI_TalonSRX(3);
	private WPI_TalonSRX right2 = new WPI_TalonSRX(4);
		
	// Gyro sensor
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static int forwardOrReverse = -1;
	
	/**
	 * Constructor, sets up motors, prevents brownouts, and minimizes pedestrian
	 * casualties.
	 */
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
    	
    	left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	
    }
    
    /**
	 * ArcadeDriveCmd will always run when other commands are not busy. This
	 * will allow operator control when the robot is not driving itself around.
	 */
    public void initDefaultCommand() {
    	
    	setDefaultCommand(new ArcadeDriveCmd());
    	
    }
    
    /**
	 * Updates the motors with what speed to drive at.
	 * 
	 * @param leftPower
	 *            Double speed of left motors. Range -1 to 1
	 * @param rightPower
	 *            Double speed of right motors. Range -1 to 1
	 */
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
    
    /**
	 * Sets the motors to be off.
	 */
    public void stop() {
    	
    	this.driveLR(0, 0);
    	
    }
    
    /**
	 * Calibrates gyro (takes 5 seconds while robot does nothing) Do this when
	 * robot first turns on.
	 */
    public void calibrateGyro() {
    	
    	gyro.calibrate();
    	
    }
    
    /**
	 * Sets the drivetrain gyro back to 0 degrees
	 */
    public void resetGyro() {
    	
    	gyro.reset();
    	
    }
    
    /**
	 * @return the current rate of turning in degrees per second
	 */
    public double getGyroRate() {
    	
    	return gyro.getRate();
    	
    }
    
	/**
	 * 
	 * @return gets an approximation of the gyro angle since reset was last
	 *         called from an accumulation using the FPGA. Will accumulate error
	 *         over time.
	 * 
	 */
    public double getGyroAngle() {
    	
    	return gyro.getAngle();
    	
    }
    
    /**
	 * Sets the drivetrain encoders back to 0 pulses
	 */
    public void resetEncoders() {
    	
    	Robot.dt.left1.getSensorCollection().setPulseWidthPosition(0, 0);
    	Robot.dt.right1.getSensorCollection().setPulseWidthPosition(0, 0);
    	//DriverStation.reportWarning("TEsting", false);
    }
    
    /**
	 * 
	 * @return gets an approximation of the distance traveled in inches
	 *         since reset was last called. Will accumulate error
	 *         over time.
	 * 
	 */
    public double getDistanceTraveled() {
    	
    	return -((double) Robot.dt.left1.getSensorCollection().getPulseWidthPosition() / 4096.0) * 6 * Math.PI;
    	
    }
    
}

