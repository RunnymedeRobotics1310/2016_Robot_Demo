package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.RaiseArmCommand;
import robot.commands.auto.base.DriveToDistance;
import robot.commands.drive.RotateToAngleCommand;

public class CrossRampartsCommand extends CommandGroup {

	public CrossRampartsCommand() {
		//THIS IS SO SKETCHY. AND IT MAY NOT WORK AT ALL. BE WARNED.
		addSequential(new RaiseArmCommand());
		addSequential(new RotateToAngleCommand(20.0, 2));
		addSequential(new DriveToDistance(1.0, 0, 150));
	}
}
