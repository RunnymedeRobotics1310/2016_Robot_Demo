package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Robot;
import robot.RobotMap;
import robot.commands.drive.RotateToAngleWithPIDCommand;

/**
 * This command tracks a vision target and shoots as soon as the vision is at
 * the center of the camera's FOV.
 */
public class AlignAndShootHighShotCommand extends CommandGroup {
	public AlignAndShootHighShotCommand() {

		double pixelDifference = Robot.oi.getVisionTargetCenter() - 83.0;
		double angleDifference = pixelDifference * RobotMap.DEGREES_PER_PIXEL;

		addParallel(new RotateToAngleWithPIDCommand());
		addSequential(new RetractBoulderCommand());
		addSequential(new WindupCommand());
		addSequential(new ShootHighGoalCommand());
	}
}
