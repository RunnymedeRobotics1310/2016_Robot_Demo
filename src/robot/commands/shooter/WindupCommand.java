package robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class WindupCommand extends Command {

    public WindupCommand() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterSubsystem.startShooterMotor();
    }

    protected boolean isFinished() {
        return Robot.shooterSubsystem.getShooterSpeed() > 100;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
