package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;

public class WindupCommand extends Command {

    public WindupCommand() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	if (Robot.shooterSubsystem.isBoulderRetracted()) {
    		Robot.shooterSubsystem.startShooterMotor();
    	}
    	Robot.shooterSubsystem.setRailPosition(true);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (Robot.shooterSubsystem.getShooterSpeed() > 80) { return true; }
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
