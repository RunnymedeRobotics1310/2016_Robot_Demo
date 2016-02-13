package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class HighShotSetupCommand extends Command {

	public HighShotSetupCommand() {
		requires(Robot.shooterSubsystem);
	}

	protected void initialize() {
		Robot.shooterSubsystem.intakeEncoderReset();
	}

	protected void execute() {
		Robot.shooterSubsystem.intakeMotorReverse(IntakeReverseSpeed.LOW);
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
