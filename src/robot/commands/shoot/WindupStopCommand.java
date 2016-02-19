package robot.commands.shoot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class WindupStopCommand extends Command {

    public WindupStopCommand() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	Robot.shooterSubsystem.stopShooterMotor();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	Robot.shooterSubsystem.setRailPosition(Value.kReverse);
    }

    protected void interrupted() {
    }
}
