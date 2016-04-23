
package robot.commands.auto.base;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public abstract class AutoGoStraightCommandNoGyro extends Command {

	public enum Direction {
		FORWARD, BACKWARD;
	}
	
	private double speedSetpoint;

	public AutoGoStraightCommandNoGyro() {
		requires(Robot.chassisSubsystem);
	}

	public void setSpeed(double speed, Direction direction) {
		double absoluteSpeed = Math.abs(speed);
		this.speedSetpoint = (direction == Direction.FORWARD) ? absoluteSpeed : -absoluteSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double speed = speedSetpoint;
		Robot.chassisSubsystem.setSpeed(speed, speed);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	public void end() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
