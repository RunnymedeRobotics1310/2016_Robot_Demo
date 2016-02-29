package robot.commands.auto.base;

import robot.Robot;

public class DriveToCenterProximity extends AutoGoStraightCommand {

	private double speedSetpoint;

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
