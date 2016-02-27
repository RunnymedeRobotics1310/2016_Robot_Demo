package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.RaiseArmCommand;
import robot.commands.auto.base.DriveToDistance;
import robot.commands.auto.base.DriveToProximity;

public class CrossRockWallCommand extends CommandGroup {

	public CrossRockWallCommand() {
		addSequential(new RaiseArmCommand());
		addSequential(new DriveToProximity(0.5, 0));
		addSequential(new DriveToDistance(0.5, 0, 50));
	}
}
