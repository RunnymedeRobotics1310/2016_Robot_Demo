package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.LowerArmCommand;
import robot.commands.arm.RaiseArmCommand;
import robot.commands.auto.base.DriveToDistance;
import robot.commands.auto.base.DriveToProximity;

public class CrossPortcullisCommand extends CommandGroup {

	public CrossPortcullisCommand() {
		addSequential(new RaiseArmCommand());
		addSequential(new DriveToProximity(0.5, 0));
		addSequential(new DriveToDistance(1.0, 0, 15));
		addSequential(new LowerArmCommand());
		addSequential(new DriveToDistance(1.0, 0, 150));
	}
}
