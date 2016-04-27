package robot.commands.auto.base;

import edu.wpi.first.wpilibj.command.Command;
import robot.Field.Slot;
import robot.Robot;

/**
 * This command will attempt to wait for obstacles to move out of the way.
 * Only works while the robot is moving away from the left wall in order to
 * end at the specified {@link robot.Field.Target Target}.
 */
public class WaitUntilPathClear extends Command {

	private Slot slot;

	public WaitUntilPathClear(double waitTime, Slot slot) {
		this.setTimeout(waitTime);
		this.slot = slot;
		requires(Robot.chassisSubsystem);
	}

	@Override
	protected void initialize() {
		// Do nothing. At all.
	}

	@Override
	protected void execute() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	@Override
	protected void end() {

	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() 
				|| Robot.chassisSubsystem.getRearUltrasonicDistance() >= 
										slot.getDistanceToLeftWall();

	}

	@Override
	protected void interrupted() {
	}

}
