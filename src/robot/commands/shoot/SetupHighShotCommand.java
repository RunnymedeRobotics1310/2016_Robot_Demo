package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetupHighShotCommand extends CommandGroup {
	public SetupHighShotCommand() {
		addSequential(new RetractBoulderCommand());
		addSequential(new WindupCommand());
	}
}
