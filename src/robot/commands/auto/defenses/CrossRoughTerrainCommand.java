package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.RaiseArmCommand;
import robot.commands.auto.base.DriveToDistance;

public class CrossRoughTerrainCommand extends CommandGroup {

	public CrossRoughTerrainCommand() {
		addSequential(new RaiseArmCommand());
		addSequential(new DriveToDistance(1.0, 0, 150));
	}
}
