package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class RotateToAngleCommandNoGyro extends Command {

	private double targetAngle;
	private double leftSpeed;
	private double rightSpeed;
	private double initSpeed = 0.5;

	private double encoderCounts;
	private double encoderDistance;
	
	public RotateToAngleCommandNoGyro(double targetAngle, double time) {
		requires(Robot.chassisSubsystem);
		this.targetAngle = targetAngle;
		this.setTimeout(time);
		
		this.encoderCounts = 24.3 * targetAngle;
		this.encoderDistance = Robot.chassisSubsystem.getRawEncoderDistance() + encoderCounts;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Which way am I going to turn? Need to be able to get angle from
		// chassis

		leftSpeed = (targetAngle > 0) ? initSpeed : -initSpeed;
		rightSpeed = -leftSpeed;

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
		System.out.println("Wait finished");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Reduce the speed as you are getting close to the target.

		leftSpeed = (targetAngle > 0) ? initSpeed : -initSpeed;
		rightSpeed = -leftSpeed;

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
		System.out.println("Wait finished");

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.chassisSubsystem.getRawEncoderDistance() >= encoderDistance;
	}

	// Called once after isFinished returns true
	protected void end() {
		// stop motors
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
