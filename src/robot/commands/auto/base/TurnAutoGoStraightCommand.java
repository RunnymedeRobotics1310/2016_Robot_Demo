
package robot.commands.auto.base;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;
import robot.pids.TurnGoStraightPID;

public abstract class TurnAutoGoStraightCommand extends Command {

	public enum Direction {
		FORWARD, BACKWARD;
	}

	private double angleSetpoint;
	private double speedSetpoint;

	public TurnAutoGoStraightCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	public void setSpeed(double speed, Direction direction) {
		double absoluteSpeed = Math.abs(speed);
		this.speedSetpoint = (direction == Direction.FORWARD) ? absoluteSpeed : -absoluteSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		TurnGoStraightPID.setEnabled(false);
		TurnGoStraightPID.setSetpoint(angleSetpoint);
		TurnGoStraightPID.setEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double speed = speedSetpoint;
		double leftSpeed;
		double rightSpeed;

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("AnglePIDOutput", TurnGoStraightPID.getOutput());

		double turn = TurnGoStraightPID.getOutput();
/*
		// Reverse the direction of the turn when going backwards
		if (speed < 0) {
			turn = -turn;
		}

		// If the speed is zero, then just pivot in place
		// The speed of the turn is set to 1/4 of the full value for all pivots.
		if (Math.abs(speed) < 0.03) {
			leftSpeed = turn / 4.0;
			rightSpeed = -turn / 4.0;
		} else {

			// If the speed is more than zero, then slow down one side of the
			// robot
			leftSpeed = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn < 0) ? speed : speed * (1 - turn);
		}*/
		leftSpeed = turn;
		rightSpeed = -turn;

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	public void end() {
		TurnGoStraightPID.setEnabled(false);
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
