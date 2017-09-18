package robot.commands.auto.base;

import robot.Robot;

/**
 * This command drives the robot in the specified direction until at least
 * one of the proximity sensors (left or right) is activated. For driving
 * until there's an obstacle in front of the robot see {@link DriveToCenterProximity}.
 */
public class DriveToProximity extends AutoGoStraightCommand {

	private double speedSetpoint;

	/**
	 * 
	 * @param speed Speed to drive at.
	 * @param angle The angle to drive at (in degrees).
	 */
	public DriveToProximity(double speed, double angle) {
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
		return Robot.chassisSubsystem.getProximity();
	}
	
	@Override
	protected void end() {
		Robot.chassisSubsystem.setSpeed(0.0, 0.0);
	}
}
