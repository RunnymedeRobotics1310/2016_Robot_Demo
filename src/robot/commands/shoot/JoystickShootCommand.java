
package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.R_GameController.Button;
import robot.R_GameController.Trigger;
import robot.Robot;

/**
 *
 */
public class JoystickShootCommand extends Command {

	public JoystickShootCommand() {
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		// Look for a button and start the intake. (do nothing if we already
		// have a ball)
		if (Robot.oi.getIntakeStart()) {
			// TODO Replace with command group
			if (!Robot.shooterSubsystem.isBoulderLoaded()) {
				Scheduler.getInstance().add(new PickupBoulderCommand());
				return;
				//and actuate arms
			} else {
				//just actuate arms
			}
		}
		
		// Look for a button for high shot (needs to have a ball retracted and 
		// the shooter must be up to speed.
		if (Robot.oi.getShootHighGoal()) {
			
			if ( Robot.shooterSubsystem.isBoulderRetracted() ) {
				
				if ( Robot.shooterSubsystem.getShooterSpeed() > 100) {
					
					Scheduler.getInstance().add(new ShootHighGoalCommand());
					Robot.shooterSubsystem.setBallRetracted(false);
					return;

				}
			
			} else {
			
				Scheduler.getInstance().add(new SetupHighShotCommand());
				Robot.shooterSubsystem.setBallRetracted(true);
				return;
				
			}
		}
		
		// Look for a button for low shot (needs to have a ball loaded)

		if (   Robot.oi.getShootLowGoal() 
			&& (   Robot.shooterSubsystem.isBoulderLoaded()
				|| Robot.shooterSubsystem.isBoulderRetracted()) ) {
			Scheduler.getInstance().add(new ShootLowGoalCommand());
			return;
		}

		// TODO Figure out how to cancel a command
		if (Robot.oi.getCancel()) {
			Scheduler.getInstance().add(new WindupStopCommand());
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
