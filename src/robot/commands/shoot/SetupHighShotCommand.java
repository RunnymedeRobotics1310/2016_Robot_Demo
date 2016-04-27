package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command starts the high goal shooting routine. This consists of
 * {@link RetractBoulderBoulderCommand} and {@link WindupCommand}.
 */
public class SetupHighShotCommand extends CommandGroup {
	public SetupHighShotCommand() {
		addSequential(new RetractBoulderCommand());
		addSequential(new WindupCommand());
	}
}
