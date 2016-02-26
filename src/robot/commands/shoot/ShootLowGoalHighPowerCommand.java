package robot.commands.shoot;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class ShootLowGoalHighPowerCommand extends Command {

	double waitTime = 1.0;
	double startTime;
	
	public ShootLowGoalHighPowerCommand() {
		requires(Robot.shooterSubsystem);
		requires(Robot.armSubsystem);
		this.setTimeout(2);
	}

	@Override
	protected void execute() {		
		Robot.armSubsystem.startArmIntakeReverse();
		Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.INTAKE_LEVEL.getAngle());
		
		if (startTime + waitTime < this.timeSinceInitialized()) {
			Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.HIGH);
			Robot.shooterSubsystem.startShooterMotorReverse();
		}
	}

	@Override
	protected void initialize() {
		Robot.shooterSubsystem.resetIntakeEncoder();
		startTime = this.timeSinceInitialized();
	}
	
	@Override
	protected void end() {
		Robot.shooterSubsystem.stopIntakeMotor();
		Robot.shooterSubsystem.stopShooterMotor();
		Robot.armSubsystem.stopArmIntake();
		Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.INTAKE_LEVEL.getAngle());
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void interrupted() {
		
	}

}