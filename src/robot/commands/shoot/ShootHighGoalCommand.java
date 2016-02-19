package robot.commands.shoot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ShootHighGoalCommand extends Command {

	public ShootHighGoalCommand() {
		requires(Robot.shooterSubsystem);
	}

	@Override
	protected void execute() {
		Robot.shooterSubsystem.startIntakeMotor();
	}

	@Override
	protected void initialize() {
		this.setTimeout(1.5);
		Robot.shooterSubsystem.startIntakeMotor();
	}
	
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.shooterSubsystem.stopShooterMotor();
		if (Robot.shooterSubsystem.getRailPosition() != Value.kReverse) {
			Robot.shooterSubsystem.setRailPosition(Value.kReverse);
		}
	}

	@Override
	protected void interrupted() {
		
	}
	
}
