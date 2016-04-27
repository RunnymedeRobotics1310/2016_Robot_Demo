package robot.commands.auto.base;

import robot.Robot;
import robot.RobotMap.UltrasonicPosition;

/**
 * This command drives the robot to the specified distance. The distance is
 * measured using either of the ultrasonic sensors on the robot.
 */
public class DriveToUltraDistance extends AutoGoStraightCommand {

	private double distanceSetpoint;

	private double speedSetpoint;
	
	private UltrasonicPosition direction;

	/**
	 * @param speed
	 *            The speed at which to drive.
	 * @param angle
	 *            The angle to drive at (in degrees).
	 * @param distance
	 *            The distance to drive to.
	 */
	public DriveToUltraDistance(double speed, double angle, double distance, UltrasonicPosition direction) {
		super(angle);
		this.speedSetpoint = speed;
		this.distanceSetpoint = distance;
		this.direction = direction;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.chassisSubsystem.resetUltrasonicSensorFilter();
		switch (direction) {
		case FRONT:
			if (distanceSetpoint - Robot.chassisSubsystem.getFrontUltrasonicDistance() < 0) {
				setSpeed(speedSetpoint, Direction.FORWARD);
			} else {
				setSpeed(speedSetpoint, Direction.BACKWARD);
			}
			break;
		case REAR:
			if (distanceSetpoint - Robot.chassisSubsystem.getRearUltrasonicDistance() < 0) {
				setSpeed(speedSetpoint, Direction.BACKWARD);
			} else {
				setSpeed(speedSetpoint, Direction.FORWARD);
			}
			break;
		}
	}

	/**
	 * Gets the distance set point.
	 * 
	 * @return the distance set point.
	 */
	public double getDistanceSetpoint() {
		return distanceSetpoint;
	}

	// Called once after isFinished returns true
	@Override
	protected boolean isFinished() {
		// Stop 4" early because it takes the robot 12 inches to stop.
		switch (direction) {
		case FRONT:
			return (Math.abs(distanceSetpoint - Robot.chassisSubsystem.getFrontUltrasonicDistance()) <= 12);
		case REAR:
			return (Math.abs(distanceSetpoint - Robot.chassisSubsystem.getRearUltrasonicDistance()) <= 12);
		}
		return true;

	}
}