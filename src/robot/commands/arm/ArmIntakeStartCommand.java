package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ArmIntakeStartCommand extends Command {

	/**
	 * This command starts the arm intake motor at full speed.
	 */
    public ArmIntakeStartCommand() {
    	requires(Robot.armSubsystem);
    }

    @Override
    protected void initialize() {
    	Robot.armSubsystem.startArmIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.armSubsystem.stopArmIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
