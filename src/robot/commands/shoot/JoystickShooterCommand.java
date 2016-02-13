
package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.R_GameController.Button;
import robot.Robot;

/**
 *
 */
public class JoystickShooterCommand extends Command {

	public JoystickShooterCommand() {
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Look for a button and start the intake. (do nothing if we already have a ball)
		if (Robot.oi.getButton(Button.LEFT_BUMPER) && !Robot.shooterSubsystem.isBoulderLoaded()){
			Scheduler.getInstance().add(new PickupBoulderCommand());
		}
		// Look for a button for high shot (needs to have a ball loaded)
		if (Robot.oi.getButton(Button.B) && Robot.shooterSubsystem.isBoulderLoaded()){
			//TODO Schedule a command group for the high shot.
		}
		// Look for a button for low shot (needs to have a ball loaded)
		
		if (Robot.oi.getButton(Button.A) && Robot.shooterSubsystem.isBoulderLoaded()){
			Scheduler.getInstance().add(new LowShotCommand());
		}
		
		// Look for a button to lower the arm
		if (Robot.oi.getButton(Button.RIGHT_BUMPER)){
			//TODO Schedule a command to lower the arm.
		}
		
		//TODO This "if" should control how far up/down the arm is
		//if (Robot.oi.getTrigger(Trigger.LEFT))
		
		//TODO Figure out how to cancel a command
		if (Robot.oi.getButton(Button.X)){
			
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
