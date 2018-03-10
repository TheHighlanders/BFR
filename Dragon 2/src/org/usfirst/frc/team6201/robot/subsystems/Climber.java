package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	private VictorSP top = new VictorSP(RobotMap.CLIMBER_TOP);
    private VictorSP bottom = new VictorSP(RobotMap.CLIMBER_BOTTOM);

    public void climb() {
       	
        top.set(-0.9);
       	bottom.set(-0.9);
       	
    }
    
    public void stop() {
       	
        top.set(0);
       	bottom.set(0);
       	
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

