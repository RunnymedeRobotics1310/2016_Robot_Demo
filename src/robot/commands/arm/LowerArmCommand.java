package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;

public class LowerArmCommand extends Command {

	public LowerArmCommand() {
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
		Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.INTAKE_LEVEL.getAngle());
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
