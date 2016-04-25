package robot.commands.auto.base;

import robot.Robot;

/**
 * This commands drives the robot until the proximity sensor at the front of the
 * robot is activated.
 */
public class DriveToCenterProximity extends AutoGoStraightCommand {

	private double speedSetpoint;

	/**
	 * @param speed
	 *            Speed at which to drive.
	 * @param angle
	 *            The angle to drive at (in degrees).
	 */
	public DriveToCenterProximity(double speed, double angle) {
		super(angle);
		this.speedSetpoint = speed;
	}

	@Override
	protected void initialize() {
		super.initialize();
		setSpeed(speedSetpoint, Direction.FORWARD);
	}

	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.getCenterProximity();
	}

	public void end() {
		Robot.chassisSubsystem.setSpeed(0.0, 0.0);
	}
}
