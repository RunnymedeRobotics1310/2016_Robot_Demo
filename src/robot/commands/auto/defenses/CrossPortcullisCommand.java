package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.RobotMap.ArmLevel;
import robot.commands.arm.SetArmLevelCommand;
import robot.commands.auto.base.DriveToDistance;
import robot.commands.auto.base.DriveToProximity;

/**
 *	This CommandGroup contains the routine required to cross the Portcullis.
 */

public class CrossPortcullisCommand extends CommandGroup {

	public CrossPortcullisCommand() {
		addSequential(new SetArmLevelCommand(ArmLevel.LOW_LEVEL));
		addSequential(new DriveToProximity(0.8, 0));
		addSequential(new DriveToDistance(1.0, 0, 110));
	}
}
