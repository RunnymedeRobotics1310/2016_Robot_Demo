package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;

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
			if (!Robot.shooterSubsystem.isBoulderLoaded()) {
				Robot.shooterSubsystem.setBallRetracted(false);
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
			System.out.println("Ball rectracted; " + Robot.shooterSubsystem.isBoulderRetracted());
			if ( Robot.shooterSubsystem.isBoulderRetracted() ) {
				
				if ( Robot.shooterSubsystem.getShooterSpeed() > 100) {
					
					Robot.shooterSubsystem.setBallRetracted(false);
					Scheduler.getInstance().add(new ShootHighGoalCommand());
					
					return;

				}
			
			} else {
				Robot.shooterSubsystem.setBallRetracted(true);
				Scheduler.getInstance().add(new SetupHighShotCommand());
				
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

		if (Robot.oi.getCancel()) {
			Scheduler.getInstance().add(new WindupStopCommand());
			Robot.shooterSubsystem.setBallRetracted(false);
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
