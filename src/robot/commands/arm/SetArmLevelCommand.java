package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
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
		switch (level) {
		case DRIVE_LEVEL:
			Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.DRIVE_LEVEL.getAngle());
			break;
		case INTAKE_LEVEL:
			Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.INTAKE_LEVEL.getAngle());
			break;
		case LOW_LEVEL:
			Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.LOW_LEVEL.getAngle());
			break;
		case SHOOT_LEVEL:
			Robot.armSubsystem.setArmAngle(RobotMap.ArmLevel.SHOOT_LEVEL.getAngle());
			break;
		}
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
