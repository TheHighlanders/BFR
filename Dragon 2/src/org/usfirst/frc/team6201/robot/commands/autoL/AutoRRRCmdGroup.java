package org.usfirst.frc.team6201.robot.commands.autoL;

import org.usfirst.frc.team6201.robot.commands.AutoGripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.DriveDistCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorConstantForceCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorMidScaleCmd;
import org.usfirst.frc.team6201.robot.commands.ElevatorTopScaleCmd;
import org.usfirst.frc.team6201.robot.commands.GripperPushCmd;
import org.usfirst.frc.team6201.robot.commands.SmallLiftCmd;
import org.usfirst.frc.team6201.robot.commands.TurnAngleCmd;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Aims for scale on other side
 */
public class AutoRRRCmdGroup extends CommandGroup {

    public AutoRRRCmdGroup() {
    	
    	

    	DriverStation.reportWarning("R-Autonomous Procedure LLL initiated.", false);
    	addSequential(new SmallLiftCmd());
    	DriverStation.reportWarning("Pre-addSequential() message #2.", false);
    	addParallel(new ElevatorConstantForceCmd());
    	addSequential(new DriveDistCmd(190, 6, 0.6));
    	DriverStation.reportWarning("Went past the switch, Stage 1 complete.", false);
    	addSequential(new TurnAngleCmd(90, 10));
    	DriverStation.reportWarning("Robot turned, Stage 2 complete.", false);
    	addSequential(new DriveDistCmd(160, 12, 0.6));
    	DriverStation.reportWarning("Reached opposite side, Stage 3 complete.", false);
    	
    	addSequential(new TurnAngleCmd(90, 10));
    	DriverStation.reportWarning("Robot turned #2, Stage 4 complete.", false);
    	//addSequential(new ElevatorTopScaleCmd());
    	//DriverStation.reportWarning("Elevator ascension achieved, Stage 4 complete." , false);
    	addSequential(new DriveDistCmd(30, 6, 0.6));
    	addSequential(new AutoGripperPushCmd());
    	DriverStation.reportWarning("Cube ejected, Stage 5 complete.", false);
    	DriverStation.reportWarning("R-Autonomous Procedure LLL complete.", false);
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
