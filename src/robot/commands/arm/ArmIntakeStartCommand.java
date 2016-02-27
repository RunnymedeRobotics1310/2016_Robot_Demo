package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ArmIntakeStartCommand extends Command {

    public ArmIntakeStartCommand() {
    	requires(Robot.armSubsystem);
    }

    protected void initialize() {
    	Robot.armSubsystem.startArmIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armSubsystem.stopArmIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
