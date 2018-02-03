package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command interfaces between the Operator Interface object (Robot.oi) 
 * and the DriveTrain object (Robot.dt), using a tangent function to provide
 * smooth, intuitive joystick control with fine control at slow speeds, while 
 * still being able to reach full speed. Recently added support for a third 
 * axis to control the sensitivity of the joystick.
 *
 * @author David Matthews
 * @author Adriana Massie
 * @author Baxter Ellard
 *
 */
public class ArcadeDriveCmd extends Command {

	/**
	 * Calibrated turning amount by throwing the X axis of the joystick through
	 * a tan function
	 */
	private double tanTurn;
	
	/**
	 * Calibrated forward motion by throwing the Y axis of the joystick through
	 * a tan function
	 */
	private double tanPower;
	
	/**
	 * Calculates how much the robot can turn based on what the processedPower
	 * is, how much room we have to play with the motors and how much turning is
	 * desired.
	 */
	private double processedTurn;
	
	/**
	 * reserves 5% for turning at all times.
	 */
	private double processedPower;
	
	/**
	 * Creates the sensitivity curve for the Y axis
	 */
	private final double TANDOMAIN_Y = 1.3;
	
	/**
	 * Creates the sensitivity curve for the X axis
	 */
	private final double TANDOMAIN_X = 1.3;
	
	/**
	 * 
	 * @param rawVal
	 *            Value to be processed by the tangent function
	 * @param domain
	 *            Domain of the tangent function. Will effect shape of this
	 *            mapping function curve.
	 * @return A double corresponding mapped from the input rawVal via a tangent
	 *         curve and the domain. provides high motion control at slow
	 *         speeds, and full robot speed.
	 */
	private double scaledValTan(double rawVal, double domain) {
		
		return Math.tan(rawVal * domain) / (Math.tan(domain));
		
	}
	
	/**
	 * Requires the Robot.dt Drivetrain subsystem to run. Used for resource
	 * allocation in WPILibj
	 */
    public ArcadeDriveCmd() {
        
    	requires(Robot.dt);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
	 * Gets the joystick position from the Driver Station, and calculates what
	 * to set the power of the motors by using a tangent curve as a mapping
	 * function, and using the gyro to correct for some natural turning of the
	 * robot.
	 */
    protected void execute() {
    	
    	// get joystick positions
    	double joystickX = Robot.oi.getXAxisOfArcade();
    	double joystickY = Robot.oi.getYAxisOfArcade();
    	double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
    	
    	// use mapping function and the joystick slider as a gain
    	// to get a desired turn amount and a desired forward motion speed
    	tanTurn = scaledValTan(joystickX * joystickSlider, TANDOMAIN_X);
    	tanPower = scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y);
    	
    	processedPower = tanPower * 0.90;
    	
    	// Combine the desired turn rate with how much the motors are not using.
    	processedTurn = (1 - Math.abs(processedPower)) * tanTurn;
/*  
 * 		TODO: Create a data logger to do this instead of reporting all data points in this class.
 * 		  	
 *   	DriverStation.reportWarning("Arcade Y: " + Robot.oi.getYAxisOfArcade(), false);
 *   	DriverStation.reportWarning("Arcade X: " + Robot.oi.getXAxisOfArcade(), false);
 *   	DriverStation.reportWarning("Processed Power: " + processedPower, false);
 *   	DriverStation.reportWarning("Processed Turn: " + processedTurn, false);
 *   	DriverStation.reportWarning("Tan Power: " + tanPower, false);
 *   	DriverStation.reportWarning("Tan Turn: " + tanTurn, false);
 *   	DriverStation.reportWarning("Joystick X: " + joystickX,  false);
 *   	DriverStation.reportWarning("Joystick Y: " + joystickY, false);
 *   	DriverStation.reportWarning("Joystick Slider: " + joystickSlider, false);
 *   	DriverStation.reportWarning("Scaled Val Turn: " + scaledValTan(joystickX * joystickSlider, TANDOMAIN_X), false);
 *   	DriverStation.reportWarning("Scaled Val Power: " + scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y), false);
 *   
 */    	
    	
    	// Calculates the speed of the wheels to achieve the desired turning rate
    	// Checks which side of the robot is considered the "front", and inverts
    	// the Robot.dt.driveLR() parameters if needed
    	Robot.dt.driveLR(-(processedPower - processedTurn), -(processedPower + processedTurn));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
        
    }

    // Called once after isFinished returns true
    // stops the motors
    protected void end() {
    	
    	Robot.dt.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	this.end();
    	
    }
   
}
