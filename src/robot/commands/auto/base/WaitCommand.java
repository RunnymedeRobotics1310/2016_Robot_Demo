package robot.commands.auto.base;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command take over the {@link robot.subsystems.ChassisSubsystem ChassisSubsystem} and does absolutely
 * nothing. It might be useful for debugging autonomous patterns.
 */
public class WaitCommand extends Command {

	public WaitCommand(double waitTime) {
		this.setTimeout(waitTime);
		requires(Robot.chassisSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
