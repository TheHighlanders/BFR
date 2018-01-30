package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public DifferentialDrive drive;
	
    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(2);
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
    
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(4);
    
    private Joystick joystick;
    
    public DriveTrain() {
    	
    	drive = new DifferentialDrive(frontLeft, frontRight);
    	joystick = new Joystick(1);
    	
    }
	
    public void driveLR(double leftPower, double rightPower) {
    	
    	drive.tankDrive(joystick.getY(), joystick.getY());
    	
    }
    
    public void initDefaultCommand() {

    	setDefaultCommand(new ArcadeDriveCmd());
    
    }
    
    
    
}

