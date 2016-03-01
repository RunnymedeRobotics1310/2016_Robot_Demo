package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class PickupBoulderCommand extends Command {

	boolean timerSet = false;

	public PickupBoulderCommand() {
		requires(Robot.shooterSubsystem);
		requires(Robot.armSubsystem);
	}

	protected void initialize() {
		timerSet = false;
	}

	protected void execute() {
		// Robot.shooterSubsystem.resetIntakeEncoder();
		if (Robot.shooterSubsystem.getShooterSpeed() == 0) {
			Robot.shooterSubsystem.startIntakeMotor();
			if (Robot.oi.getBallStuckButton()) {
				Robot.armSubsystem.startArmIntakeReverse();
			} else {
				Robot.armSubsystem.startArmIntake();
			}
			Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.INTAKE_LEVEL.getAngle());
		}
	}

	protected boolean isFinished() {
		if (Robot.shooterSubsystem.isBoulderLoaded() && !timerSet) {
			// If the boulder is loaded, and the timer is false, initial
			this.setTimeout(this.timeSinceInitialized() + 0.25);
			timerSet = true;
		}

		return (timerSet && this.isTimedOut()) || Robot.oi.getCancel();
	}

	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.armSubsystem.stopArmIntake();
		Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.DRIVE_LEVEL.getAngle());
	}

	protected void interrupted() {
	}
}
