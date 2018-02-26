package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command starts the shooter motor, and waits until the shooter is at the
 * target speed before finishing.
 */
public class WindupCommand extends Command {

	double speedSetPointPercent;

	public WindupCommand() {
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		// Shooter speeds.
		// Low speed = 80rps - OI input = +1.0
		// High speed = 95rps - OI input = -1.0

		speedSetPointPercent = 0.1 * (-Robot.oi.getShootSpeed() + -1) + 0.75;

		if (Robot.shooterSubsystem.isBoulderRetracted()) {
			Robot.shooterSubsystem.setShooterSpeed(speedSetPointPercent);
		}

		Robot.shooterSubsystem.setRailPosition(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		speedSetPointPercent = 0.1 * (-Robot.oi.getShootSpeed() + 1) + 0.75;
		Robot.shooterSubsystem.setShooterSpeed(speedSetPointPercent);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// Finish the wind up when at 94% of the target.
		if (Robot.shooterSubsystem.getShooterSpeed() > speedSetPointPercent * 0.94 * 100) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
