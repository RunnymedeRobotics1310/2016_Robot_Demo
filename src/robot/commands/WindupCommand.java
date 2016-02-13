package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class WindupCommand extends Command {

    public WindupCommand() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	Robot.shooterSubsystem.startShooterMotor();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
