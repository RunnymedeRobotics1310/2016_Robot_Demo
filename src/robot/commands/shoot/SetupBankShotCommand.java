package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command is used for doing a bank shot. The bank shot should be performed
 * only while climbing. This consists {@link RetractBoulderCommand} and
 * {@link WindupCommand}.
 */
public class SetupBankShotCommand extends CommandGroup {
	private static boolean ARM_FULLY_UP = true;

	public SetupBankShotCommand() {
		addSequential(new RetractBoulderCommand(ARM_FULLY_UP));
		addSequential(new WindupCommand());
	}
}
