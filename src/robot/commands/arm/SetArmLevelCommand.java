package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap.ArmLevel;

public class SetArmLevelCommand extends Command {

	ArmLevel level;

	public SetArmLevelCommand(ArmLevel level) {
		requires(Robot.armSubsystem);
		this.level = level;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		Robot.armSubsystem.setArmAngle(level.getAngle());
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
