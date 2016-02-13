package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class HighShotSetup extends Command {

	public HighShotSetup() {
		requires(Robot.shooterSubsystem);
	}

	protected void initialize() {
		Robot.shooterSubsystem.intakeEncoderReset();
		Robot.shooterSubsystem.intakeMotorReverse(IntakeReverseSpeed.LOW);
	}

	protected void execute() {
		Robot.shooterSubsystem.startShooterMotor();
	}

	protected boolean isFinished() {
		return (Math.abs(Robot.shooterSubsystem.intakeEncoderGetDistance()) > 90.0);
	}

	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
	}

	protected void interrupted() {
	}
}
