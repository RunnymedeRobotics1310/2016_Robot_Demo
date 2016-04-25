package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command rotates the robot without using the gyroscope.
 */
public class RotateFixedCommand extends Command {

	public enum Direction {
		LEFT, RIGHT;
	}

	Direction direction;

	public RotateFixedCommand(Direction direction) {
		this.direction = direction;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.setTimeout(0.005);
	}
	
	// Called repeatedly when this Command is scheduled to run
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

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}
}
