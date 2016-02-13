package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ShootHighGoalCommand extends Command {

	public ShootHighGoalCommand() {
		this.setTimeout(3.0);
	}

	@Override
	protected void execute() {
		Robot.shooterSubsystem.startIntakeMotor();
	}

	@Override
	protected void initialize() {
		Robot.shooterSubsystem.startIntakeMotor();
	}
	
	@Override
	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void interrupted() {
		
	}
	
}
