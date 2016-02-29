package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class RotateFixedCommand extends Command {

	public enum Direction {
		LEFT, RIGHT;
	}

	Direction direction;

	public RotateFixedCommand(Direction direction) {
		this.direction = direction;
	}

	@Override
	protected void end() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	@Override
	protected void execute() {

		switch (direction) {
		case LEFT:
			Robot.chassisSubsystem.setSpeed(-1, 1);
			break;
		case RIGHT:
			Robot.chassisSubsystem.setSpeed(1, -1);
			break;
		}
	}

	@Override
	protected void initialize() {
		this.setTimeout(0.005);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
