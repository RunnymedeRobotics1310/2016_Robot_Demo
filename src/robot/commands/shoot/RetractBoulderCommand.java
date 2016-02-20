package robot.commands.shoot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class RetractBoulderCommand extends Command {

	boolean hasBoulder = false;
	boolean cancelButton = false;
	
	public RetractBoulderCommand() {
		requires(Robot.shooterSubsystem);
	}

	protected void initialize() {
		Robot.shooterSubsystem.resetIntakeEncoder();
		hasBoulder = Robot.shooterSubsystem.isBoulderLoaded();
	}

	protected void execute() {
		Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
		if (Robot.shooterSubsystem.getRailPosition() != Value.kForward) {
			Robot.shooterSubsystem.setRailPosition(Value.kForward);
		}
		Robot.shooterSubsystem.startShooterMotorReverse();
	}

	protected boolean isFinished() {
		
		if (Robot.shooterSubsystem.getIntakeDistance() < -180) { return true; }
		
		if (!hasBoulder) {
			if (!Robot.shooterSubsystem.isBoulderLoaded()) {
				cancelButton = Robot.oi.getCancel();
				if (cancelButton) { return true; }
				return false;
			} else {
				hasBoulder = true;
			}
		}
		return !Robot.shooterSubsystem.isBoulderLoaded();
	}

	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.shooterSubsystem.stopShooterMotor();
		if (cancelButton) {
			Robot.shooterSubsystem.setBallRetracted(false);
		}
	}

	protected void interrupted() {
	}
}
