package robot.commands.auto.defenses;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.LowerArmCommand;
import robot.commands.arm.RaiseArmCommand;
import robot.commands.auto.base.DriveToDistance;
import robot.commands.auto.base.DriveToProximity;
import robot.commands.auto.base.WaitCommand;

public class CrossChavelDeFriseCommand extends CommandGroup {

	public CrossChavelDeFriseCommand() {
		addSequential(new RaiseArmCommand());
		addSequential(new DriveToProximity(0.5, 0));
		addSequential(new LowerArmCommand());
		addSequential(new WaitCommand(3.0));
		addSequential(new DriveToDistance(0.5, 0, 80));
		addSequential(new RaiseArmCommand());
	}
}
