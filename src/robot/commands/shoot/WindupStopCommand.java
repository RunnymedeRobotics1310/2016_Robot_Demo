package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 * This command can be used as a way to stop the {@link WindupCommand}.
 */
public class WindupStopCommand extends Command {

    public WindupStopCommand() {
    	requires(Robot.shooterSubsystem);
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterSubsystem.stopShooterMotor();
    }

	// Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

	// Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.setRailPosition(false);
    }

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
    protected void interrupted() {
    }
}
