package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.drive.RotateToAngleWithPIDCommand;
import robot.commands.drive.TargetingMode;

/**
 * This command tracks a vision target and shoots as soon as the vision is at
 * the center of the camera's FOV.
 */
public class AlignAndShootHighShotCommand extends CommandGroup {
	public AlignAndShootHighShotCommand(TargetingMode targetingMode) {
		addParallel(new RotateToAngleWithPIDCommand(targetingMode));
		addSequential(new RetractBoulderCommand());
		addSequential(new WindupCommand());
		addSequential(new ShootHighGoalCommand());
	}
}
