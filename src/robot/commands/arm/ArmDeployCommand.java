package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ArmDeployCommand extends Command {

	public ArmDeployCommand() {
		requires(Robot.armSubsystem);
	}

	protected void initialize() {
		Robot.armSubsystem.deployArm();
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return Robot.armSubsystem.getArmUpperLimit() || Robot.armSubsystem.getArmLowerLimit();
	}

	protected void end() {
		Robot.armSubsystem.setArmDeploy(!Robot.armSubsystem.getArmDeploy());
	}

	protected void interrupted() {
	}
}
