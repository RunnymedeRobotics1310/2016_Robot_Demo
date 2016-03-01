package robot.commands.auto.base;

import robot.Robot;

public class DriveToProximity extends AutoGoStraightCommand {

	private double speedSetpoint;

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
		//TODO: Make another method to get only the left or right proximity instead of all three
		return Robot.chassisSubsystem.getProximity();
	}
	
	public void end() {
		Robot.chassisSubsystem.setSpeed(0.0, 0.0);
	}
}
