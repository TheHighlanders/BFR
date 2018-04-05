package org.usfirst.frc.team6201.robot.commands.auto.right;

import org.usfirst.frc.team6201.robot.commands.AutoGripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.DriveDistCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorConstantForceCmd;
import org.usfirst.frc.team6201.robot.commands.SmallLiftCmd;
import org.usfirst.frc.team6201.robot.commands.TurnAngleCmd;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Assumes we are on the right side.
 * Attempts to drop a cube on the switch.
 */
public class RAutoRLRCmdGroup extends CommandGroup {

    public RAutoRLRCmdGroup() {
    	
    	DriverStation.reportWarning("R-Autonomous Procedure RLR initiated.", false);
    	addSequential(new SmallLiftCmd());
    	DriverStation.reportWarning("Initial elevator ascension achieved, Stage 1 complete.", false);
    	addParallel(new ElevatorConstantForceCmd());
    	addSequential(new DriveDistCmd(140, 6, 0.65));
    	DriverStation.reportWarning("Reached switch, Stage 2 complete.", false);
    	addSequential(new TurnAngleCmd(-90, 10));
    	DriverStation.reportWarning("Robot turned left, Stage 3 complete.", false);
    	addSequential(new DriveDistCmd(36, 12, 0.65));
    	DriverStation.reportWarning("Elevator ascension achieved, Stage 4 complete.", false);
    	addSequential(new AutoGripperPushCmd());
    	DriverStation.reportWarning("Cube ejected, Stage 5 complete.", false);
    	DriverStation.reportWarning("R-Autonomous Procedure RLR complete.", false);
    	
    }
}
