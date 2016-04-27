package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command starts the shooter motor at full speed.
 */
public class ShootHighGoalCommand extends Command {

	public ShootHighGoalCommand() {
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.setTimeout(1.0);
		Robot.shooterSubsystem.startIntakeMotor();
	}

	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.shooterSubsystem.startIntakeMotor();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.shooterSubsystem.stopShooterMotor();
		Robot.armSubsystem.stopArmIntake();

		if (Robot.shooterSubsystem.getRailPosition()) {
			Robot.shooterSubsystem.setRailPosition(false);
		}
		Robot.chassisSubsystem.endAutoTargeting();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {

	}

}
