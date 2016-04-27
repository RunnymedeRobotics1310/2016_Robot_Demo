package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

/**
 * This command starts the intake in reverse at full speed. The shooter is also
 * started at full speed in reverse give the boulder some extra "kick".
 */
public class ShootLowGoalCommand extends Command {

	public ShootLowGoalCommand() {
		requires(Robot.shooterSubsystem);
		requires(Robot.armSubsystem);
		this.setTimeout(0.5);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.HIGH);
		Robot.shooterSubsystem.startShooterMotorReverse();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shooterSubsystem.resetIntakeEncoder();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.shooterSubsystem.stopShooterMotor();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {

	}

}