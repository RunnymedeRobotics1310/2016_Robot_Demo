package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;

/**
 * This command rotates the robot to the specified angle. The angle is relative
 * to the gyroscope's "0 degrees" angle.
 */
public class RotateToAngleCommand extends Command {

	private double targetAngle;
	private double leftSpeed;
	private double rightSpeed;
	private double initSpeed = 0.5;

	public RotateToAngleCommand(double targetAngle, double time) {
		requires(Robot.chassisSubsystem);
		this.targetAngle = targetAngle;
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Which way am I going to turn? Need to be able to get angle from
		// chassis

		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		leftSpeed = (angleDifference > 0.0) ? initSpeed : -initSpeed;
		rightSpeed = -leftSpeed;

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
		System.out.println("Wait finished");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Reduce the speed as you are getting close to the target.

		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		double speed = initSpeed;

		if (Math.abs(angleDifference) < 20.0) {
			speed *= 0.5;
		} else if (Math.abs(angleDifference) < 10.0) {
			speed *= 0.3;
		} else if (Math.abs(angleDifference) < 5.0) {
			speed *= 0.1;
		}

		leftSpeed = (angleDifference > 00) ? speed : -speed;

		rightSpeed = -leftSpeed;

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

		SmartDashboard.putNumber("Angle difference", angleDifference);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(Robot.oi.getSpeed()) > 0.05 || Math.abs(Robot.oi.getTurn()) > 0.05) {
			return true;
		} else if (Robot.oi.getPOV() != -1.0) {
			Scheduler.getInstance().add(new RotateToAngleCommand(Robot.oi.getPOV(), 3.0));
			return true;
		}
		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		if (Math.abs(angleDifference) < 3.0) {
			return true;
		} else {
			return this.isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		// stop motors
		Robot.chassisSubsystem.setSpeed(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
