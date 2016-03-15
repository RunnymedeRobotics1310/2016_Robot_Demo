package robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class JoystickClimberCommand extends Command {

	private boolean climberReleased;
	private boolean scissorUp;

	public JoystickClimberCommand() {
		requires(Robot.climberSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.climberSubsystem.winchOff();
		Robot.climberSubsystem.scissorDown();
		this.setTimeout(0);
		scissorUp = false;
		climberReleased = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (!this.isTimedOut()) {
			return;
		}

		// On the first button, just release the climber.
		if (!climberReleased) {
			if (Robot.oi.getClimbButton()) {
				Robot.climberSubsystem.releaseClimber();
				this.setTimeout(this.timeSinceInitialized() + 1.0);
				climberReleased = true;
			}
			return;
		}
		
		// On the first time the button is pressed, deploy the scissor.
		if (!scissorUp) {
			if (Robot.oi.getClimbButton()) {
				Robot.climberSubsystem.scissorUp();
				this.setTimeout(this.timeSinceInitialized() + 2.0);
				scissorUp = true;
			}
			return;
		}

		// Release the pressure on the scissor lift
		if (Robot.oi.getScissorReleaseButton()) {
			Robot.climberSubsystem.scissorDown();
		}
		
		if (Robot.oi.getClimbButton()) {
			Robot.climberSubsystem.winchOn();
			return;
		}
		
		Robot.climberSubsystem.winchOff();

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
