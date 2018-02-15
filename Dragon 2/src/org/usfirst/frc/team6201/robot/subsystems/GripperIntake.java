package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Uses ultrasonic sensors and TalonSRX motor controllers to automatically 
 * start and stop the motors on the gripper when a cube is near and when a 
 * cube is actually in the gripper, respectively. 
 * 
 * Start wheels may end up overriding the eject command
 * 
 * @author Baxter Ellard
 */
public class GripperIntake extends Subsystem {
	
	// Instantiates TalonSRX motor controllers at CAN Ports
	// 5 and 6.
	private VictorSP leftIntake = new VictorSP(RobotMap.GRIPPER_LEFT);
    private VictorSP rightIntake = new VictorSP(RobotMap.GRIPPER_RIGHT);
    
    /**
     * Constructor
     */
    public GripperIntake() {

    }
    
    /**
	 *  sucks in or maybe ejects out
	 */
   public void pull() {
   	
    leftIntake.set(1);
   	rightIntake.set(-1);
   	
   }
   
   /**
    * ejects out or maybe sucks in
    */
   public void push() {
   	
	   leftIntake.set(-1);
	   rightIntake.set(1);
   }
   
   /**
    * Sets gripper motor speeds to 0
    */
   public void stop() {
   	
	   leftIntake.set(0);
	   rightIntake.set(0);
   }
    
    public void initDefaultCommand() {
    }
}

