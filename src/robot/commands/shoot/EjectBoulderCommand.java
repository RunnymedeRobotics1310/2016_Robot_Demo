package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class EjectBoulderCommand extends Command {

	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	
    public EjectBoulderCommand() {
        requires(shooterSubsystem);
    }
    
    protected void initialize() {
    	setTimeout(2.0);
    }

    protected void execute() {
    	shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
    	// Spin the shooter motor backwards in case there is a ball stuck in the shooter.
    	shooterSubsystem.startShooterMotorReverse();
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	shooterSubsystem.stopIntakeMotor();
    	shooterSubsystem.stopShooterMotor();
    }

    protected void interrupted() {
    }
}
