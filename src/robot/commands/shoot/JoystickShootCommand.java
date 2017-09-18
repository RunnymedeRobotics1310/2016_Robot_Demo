package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;
import robot.oi.OI;
import robot.subsystems.ShooterSubsystem;

/**
 * Default command for the {@link robot.subsystems.ShooterSubsystem
 * ShoterSubsystem}. This command manages all of shooter's and intake's actions.
 */
public class JoystickShootCommand extends Command {

	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;

	public JoystickShootCommand() {
		requires(shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Look for a button and start the intake.
		// (do nothing if we already have a ball)

		if (oi.getOuterIntakeBoulderButton()) {
			if (!shooterSubsystem.isBoulderLoaded()) {
				shooterSubsystem.setBoulderRetracted(false);
				Scheduler.getInstance().add(new PickupBoulderCommand());
				return;
				// and actuate arms
			} else {
				// just actuate arms
			}

			// Dont look for other buttons
			return;

		}

		// The High Goal shooter button is used for both starting the
		// high goal spinner. The trigger is used to shoot.
		//
		// If the ball is not yet retracted, then set up for a shot by
		// retracting the ball and starting the shooter.
		//
		// If the ball is retracted, and the shooter is up to speed, then
		// take a shot.

		if (oi.getWindUpShooterButton()) {
			// Only start retracting if you have a ball
			if (shooterSubsystem.isBoulderLoaded()) {

				shooterSubsystem.setBoulderRetracted(true);
				Scheduler.getInstance().add(new SetupHighShotCommand());

				return;
			}
		}

		// The Bank Shot shooter button is used for both starting the
		// high goal spinner, and for taking a shot.
		//
		// If the ball is not yet retracted, then set up for a shot by
		// retracting the ball and starting the shooter.
		//
		// If the ball is retracted, and the shooter is up to speed, then
		// take a shot.

		if (oi.getWindUpBankShotButton()) {
			// Only start retracting if you have a ball
			if (shooterSubsystem.isBoulderLoaded()) {

				shooterSubsystem.setBoulderRetracted(true);
				Scheduler.getInstance().add(new SetupBankShotCommand());

				return;
			}
		}

		if (oi.getShootButton()) {
			if (shooterSubsystem.isBoulderRetracted()) {
				shooterSubsystem.setBoulderRetracted(false);
				Scheduler.getInstance().add(new ShootHighGoalCommand());

				return;
			}
		}

		// Look for a button for low shot (needs to have a ball loaded)

		if (oi.getExtakeBoulderButton()
				&& (Robot.shooterSubsystem.isBoulderLoaded() || Robot.shooterSubsystem.isBoulderRetracted())) {

			Scheduler.getInstance().add(new ShootLowGoalCommand());

			return;
		}

		// Look for the cancel button.
		// The cancel button ejects the boulder, and sets it to not retracted.
		if (Robot.oi.getCancel()) {
			Scheduler.getInstance().add(new EjectBoulderCommand());
			Robot.shooterSubsystem.setRailPosition(false);
			Robot.shooterSubsystem.setBoulderRetracted(false);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
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
