package robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class LowShotCommand extends Command {

	public LowShotCommand() {
		this.setTimeout(2.0);
	}

	@Override
	protected void execute() {
		Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.HIGH);
	}

	@Override
	protected void initialize() {
		Robot.shooterSubsystem.resetIntakeEncoder();
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
		// TODO Auto-generated method stub
		
	}

}