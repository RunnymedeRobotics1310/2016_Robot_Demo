package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.oi.OI;
import robot.subsystems.ShooterSubsystem;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

/**
 * Retract boulder.
 * <p>
 * The retract boulder command has a complex set of movements to ensure the boulder is sitting
 * away from the shooter wheel before the wheel starts spinning.
 * <p> 
 * Step 1: spin the shooter motor in reverse so that the boulder is touching the intake motors.
 * Step 2: retract the boulder using the intake motors.
 * Step 3: lock the intake rollers in place and stop the shooter motor from spinning in reverse.
 *
 */
public class RetractBoulderCommand extends Command {

	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI               oi               = Robot.oi;
	
	boolean cancelButton = false;
	
	public RetractBoulderCommand() {
		requires(shooterSubsystem);
	}

	protected void initialize() {
		shooterSubsystem.startShooterMotorReverse();
	}

	protected void execute() {

		// Start reversing the intake some time after the shooter starts
		// reversing
		if (timeSinceInitialized() > 0.5) {
			shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
		}
	}

	protected boolean isFinished() {

		// If the cancel button is pressed, then end
		if (oi.getCancel()) {
			cancelButton = true;
			return true;
		}
		
		if (Robot.shooterSubsystem.getIntakeDistance() < -270) { return true; }
		
		return false;
	}

	protected void end() {
		
		shooterSubsystem.stopShooterMotor();

		if (cancelButton) {
			shooterSubsystem.stopIntakeMotor();
		} else {
			shooterSubsystem.lockIntakeMotor();
		}
		
	}

	protected void interrupted() {
	}
}
