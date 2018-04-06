package org.usfirst.frc.team6201.robot.commands.auto.centre;

import org.usfirst.frc.team6201.robot.commands.AutoGripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.DriveDistCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorConstantForceCmd;
import org.usfirst.frc.team6201.robot.commands.SmallLiftCmd;
import org.usfirst.frc.team6201.robot.commands.TurnAngleCmd;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Assumes the robot is in centre position.
 * Attempts to drop a cube on the left switch.
 */
public class CAutoLeftCmdGroup extends CommandGroup {

    public CAutoLeftCmdGroup() {
        
    	DriverStation.reportWarning("C-Autonomous Procedure LEFT initiated.", false);
    	addSequential(new SmallLiftCmd());
    	DriverStation.reportWarning("Initial elevator ascensions achieved, Stage 1 complete.", false);
    	addSequential(new ElevatorConstantForceCmd());
    	addSequential(new DriveDistCmd(36, 6, 0.55));
    	DriverStation.reportWarning("Initial burst completed, Stage 2 complete.", false);
    	addSequential(new TurnAngleCmd(-90, 10));
    	DriverStation.reportWarning("Robot turned left, Stage 3 complete.", false);
    	addSequential(new DriveDistCmd(66, 6, 0.55));
    	DriverStation.reportWarning("Second burst completed, Stage 4 complete.", false);
    	addSequential(new TurnAngleCmd(90, 10));
    	DriverStation.reportWarning("Robot turned right, Stage 5 complete.", false);
    	addSequential(new DriveDistCmd(110, 10, 0.55));
    	DriverStation.reportWarning("Reached switch, Stage 6 complete.", false);
    	addSequential(new AutoGripperPushCmd());
    	DriverStation.reportWarning("Cube ejected, Stage 7 complete.", false);
    	DriverStation.reportWarning("C-Autonomous Procedure LEFT complete.", false);
    	
    }
}
