package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveCmd extends Command {

	private double tanTurn;
	private double tanPower;
	
	private double processedTurn;
	private double processedPower;
	
	private final double TANDOMAIN_Y = 1.3;
	private final double TANDOMAIN_X = 1.3;
	
	private double scaledValTan(double rawVal, double domain) {
		
		return Math.tan(rawVal * domain) / (Math.tan(domain));
		
	}
	
    public ArcadeDriveCmd() {
        
    	requires(Robot.odt);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double joystickX = Robot.oi.getXAxisOfArcade();
    	double joystickY = Robot.oi.getYAxisOfArcade();
    	double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
    	
    	tanTurn = scaledValTan(joystickX * joystickSlider, TANDOMAIN_X);
    	tanPower = scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y);
    	
    	processedPower = tanPower * 0.90;
    	processedTurn = (1 - Math.abs(processedPower)) * tanTurn;
    	
    	DriverStation.reportWarning("Arcade Y: " + Robot.oi.getYAxisOfArcade(), false);
    	DriverStation.reportWarning("Arcade X: " + Robot.oi.getXAxisOfArcade(), false);
    	DriverStation.reportWarning("Processed Power: " + processedPower, false);
    	DriverStation.reportWarning("Processed Turn: " + processedTurn, false);
    	DriverStation.reportWarning("Tan Power: " + tanPower, false);
    	DriverStation.reportWarning("Tan Turn: " + tanTurn, false);
    	DriverStation.reportWarning("Joystick X: " + joystickX,  false);
    	DriverStation.reportWarning("Joystick Y: " + joystickY, false);
    	DriverStation.reportWarning("Joystick Slider: " + joystickSlider, false);
    	DriverStation.reportWarning("Scaled Val Turn: " + scaledValTan(joystickX * joystickSlider, TANDOMAIN_X), false);
    	DriverStation.reportWarning("Scaled Val Power: " + scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y), false);
    	
    	Robot.odt.driveLR(-(processedPower - processedTurn), -(processedPower + processedTurn));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
   
}
