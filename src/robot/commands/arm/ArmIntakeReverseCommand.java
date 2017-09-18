package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ArmIntakeReverseCommand extends Command {

	/**
	 * This commands starts the arm intake at full speed in reverse, useful for
	 * low goal scoring.
	 */
    public ArmIntakeReverseCommand() {
       requires(Robot.armSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armSubsystem.startArmIntakeReverse();
    	this.setTimeout(2.0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
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
