package robot.commands.auto.base;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command drives the robot without using the gyro. This is the best
 * choice if there's too much gyro drift.
 */
public abstract class AutoGoStraightCommandNoGyro extends Command {

	public enum Direction {
		/**
		 * Drives the robot forward
		 */
		FORWARD, 
		/**
		 * Drives the robot backward
		 */
		BACKWARD;
	}
	
	private double speedSetpoint;

	/**
	 * Creates a new instance of this command.
	 */
	public AutoGoStraightCommandNoGyro() {
		requires(Robot.chassisSubsystem);
	}

	/**
	 * Sets the speed of the robot. The left and right gearboxes are set to the
	 * same speed
	 * @param speed Speed at which to drive.
	 * @param direction {@link Direction} to drive the motors at.
	 */
	public void setSpeed(double speed, Direction direction) {
		double absoluteSpeed = Math.abs(speed);
		this.speedSetpoint = (direction == Direction.FORWARD) ? absoluteSpeed : -absoluteSpeed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
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
	@Override
	protected void end() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
