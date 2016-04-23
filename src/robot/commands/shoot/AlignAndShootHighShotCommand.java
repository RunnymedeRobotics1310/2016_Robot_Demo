package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Robot;
import robot.RobotMap;
import robot.commands.drive.RotateToAngleWithPIDCommand;

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
