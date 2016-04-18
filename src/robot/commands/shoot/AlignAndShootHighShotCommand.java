package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Robot;
import robot.commands.drive.RotateToAngleWithPIDCommand;

public class AlignAndShootHighShotCommand extends CommandGroup {
	public AlignAndShootHighShotCommand(double angleDifference) {
		addParallel(new RotateToAngleWithPIDCommand(Robot.chassisSubsystem.getCurrentAngle() + angleDifference));
		addSequential(new RetractBoulderCommand());
		addSequential(new WindupCommand());
		addSequential(new ShootHighGoalCommand());
	}
}
