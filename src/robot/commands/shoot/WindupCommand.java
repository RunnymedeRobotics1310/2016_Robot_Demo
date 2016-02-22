package robot.commands.shoot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class WindupCommand extends Command {

    public WindupCommand() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	if (Robot.shooterSubsystem.isBoulderRetracted()) {
    		Robot.oi.setRumble(0.2);
    		Robot.shooterSubsystem.startShooterMotor();
    	}
    	Robot.shooterSubsystem.setRailPosition(Value.kForward);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (Robot.shooterSubsystem.getShooterSpeed() > 450) { return true; }
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
