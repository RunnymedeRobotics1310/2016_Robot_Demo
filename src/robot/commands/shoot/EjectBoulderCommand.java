package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

/**
 * This command starts the intake motor at full speed in reverse.
 */
public class EjectBoulderCommand extends Command {

	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	
    public EjectBoulderCommand() {
        requires(shooterSubsystem);
    }
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(2.0);
    }

	// Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
    	// Spin the shooter motor backwards in case there is a ball stuck in the shooter.
    	shooterSubsystem.startShooterMotorReverse();
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

	// Called once after isFinished returns true
    protected void end() {
    	shooterSubsystem.stopIntakeMotor();
    	shooterSubsystem.stopShooterMotor();
    }

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
    protected void interrupted() {
    }
}
