package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetupBankShotCommand extends CommandGroup {
	private static boolean ARM_FULLY_UP = true;
	public SetupBankShotCommand() {
		addSequential(new RetractBoulderCommand(ARM_FULLY_UP));
		addSequential(new WindupCommand());
	}
}
