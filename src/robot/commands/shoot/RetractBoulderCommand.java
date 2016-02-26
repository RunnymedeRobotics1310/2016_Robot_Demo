package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.oi.OI;
import robot.subsystems.ShooterSubsystem;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

/**
 * Retract boulder.
 * <p>
 * The retract boulder command has a complex set of movements to ensure the
 * boulder is sitting away from the shooter wheel before the wheel starts
 * spinning.
 * <p>
 * Step 1: spin the shooter motor in reverse so that the boulder is touching the
 * intake motors. Step 2: retract the boulder using the intake motors. Step 3:
 * lock the intake rollers in place and stop the shooter motor from spinning in
 * reverse.
 *
 */
public class RetractBoulderCommand extends Command {

	OI oi = Robot.oi;

	boolean cancelButton = false;
	boolean lockDelayStarted = false;
	boolean retractStarted = false;

	public RetractBoulderCommand() {
		requires(Robot.shooterSubsystem);
		requires(Robot.armSubsystem);
	}

	protected void initialize() {
		Robot.shooterSubsystem.startShooterMotorReverse();
	}

	protected void execute() {

		// Start reversing the intake some time after the shooter starts
		// reversing
		if (!retractStarted) {
			if (timeSinceInitialized() > 0.5) {
				System.out.println("Time since initialized is more than 0.5");
				Robot.shooterSubsystem.resetIntakeEncoder();
				Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
				Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.SHOOT_LEVEL.getAngle());
				retractStarted = true;
			}
		}
	}

	protected boolean isFinished() {

		// If the cancel button is pressed, then end
		if (oi.getCancel()) {
			cancelButton = true;
			return true;
		}

		if (lockDelayStarted) {

			if (isTimedOut()) {
				return true;
			}

		} else {
			// Start the lock delay after shutting off the intake motor.
			// Give the motor a chance to stop before trying to lock it.
			if (Robot.shooterSubsystem.getIntakeDistance() < -210) {
				Robot.shooterSubsystem.stopIntakeMotor();
				setTimeout(timeSinceInitialized() + 1);
				lockDelayStarted = true;
			}

		}

		return false;
	}

	protected void end() {

		Robot.shooterSubsystem.stopShooterMotor();

		if (cancelButton) {
			Robot.shooterSubsystem.stopIntakeMotor();
			Robot.shooterSubsystem.setBoulderRetracted(false);
		} else {
			Robot.shooterSubsystem.lockIntakeMotor();
			Robot.shooterSubsystem.setBoulderRetracted(true);
		}

	}

	protected void interrupted() {
	}
}
