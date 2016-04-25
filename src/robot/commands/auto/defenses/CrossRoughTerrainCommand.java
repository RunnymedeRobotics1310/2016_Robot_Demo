package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.RobotMap.ArmLevel;
import robot.commands.arm.SetArmLevelCommand;
import robot.commands.auto.base.DriveToDistance;

/**
 *	This CommandGroup contains the routine required to cross the Rough Terrain.
 */
public class CrossRoughTerrainCommand extends CommandGroup {

	public CrossRoughTerrainCommand() {
		addSequential(new SetArmLevelCommand(ArmLevel.DRIVE_LEVEL));
		addSequential(new DriveToDistance(1.0, 0, 60));
	}
}
