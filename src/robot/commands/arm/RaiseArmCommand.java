package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;

public class RaiseArmCommand extends Command {

	public RaiseArmCommand() {
		requires(Robot.armSubsystem);
	}
	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.DRIVE_LEVEL.getAngle());
		Robot.armSubsystem.startArmIntakeReverse();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
