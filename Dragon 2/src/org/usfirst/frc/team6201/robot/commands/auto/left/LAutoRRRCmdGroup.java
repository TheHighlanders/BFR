package org.usfirst.frc.team6201.robot.commands.auto.left;

import org.usfirst.frc.team6201.robot.commands.AutoGripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.DriveDistCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorConstantForceCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorMidScaleCmd;
import org.usfirst.frc.team6201.robot.commands.SmallLiftCmd;
import org.usfirst.frc.team6201.robot.commands.TurnAngleCmd;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Assumes we are on left side.
 * Attempts to drop a cube on the scale on the opposite side.
 */
public class LAutoRRRCmdGroup extends CommandGroup {

    public LAutoRRRCmdGroup() {

    	DriverStation.reportWarning("L-Autonomous Procedure RRR initiated.", false);
    	addSequential(new SmallLiftCmd());
    	DriverStation.reportWarning("Initial elevator ascension reached, Stage 1 complete.", false);
    	addParallel(new ElevatorConstantForceCmd());
    	addSequential(new DriveDistCmd(190, 6, 0.6));
    	DriverStation.reportWarning("Reached past the switch, Stage 2 complete.", false);
    	addSequential(new TurnAngleCmd(90, 10));
    	DriverStation.reportWarning("Robot turned right, Stage 3 complete.", false);
    	addSequential(new DriveDistCmd(202, 12, 0.6));
    	DriverStation.reportWarning("Reached opposite side, Stage 4 complete.", false);
    	addSequential(new TurnAngleCmd(-90, 10));
    	DriverStation.reportWarning("Robot turned left, Stage 5 complete.", false);
    	addSequential(new DriveDistCmd(134, 6, 0.6));
    	DriverStation.reportWarning("Reached scale, Stage 6 complete.", false);
    	addSequential(new TurnAngleCmd(-90, 10));
    	DriverStation.reportWarning("Robot turned left, Stage 7 complete.", false);
    	addSequential(new ElevatorMidScaleCmd());
    	DriverStation.reportWarning("Mid-scale elevator ascension achieved, Stage 8 complete.", false);
    	addSequential(new AutoGripperPushCmd());
    	DriverStation.reportWarning("Cube ejected, Stage 9 complete.", false);
    	DriverStation.reportWarning("L-Autonomous Procedure RRR complete.", false);
    	
    }
}
