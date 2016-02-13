package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ShooterSubsystem.IntakeReverseSpeed;

public class EjectBoulderCommand extends Command {

    public EjectBoulderCommand() {
        requires(Robot.shooterSubsystem);
    }
    protected void initialize() {
    	if (Robot.shooterSubsystem.isBoulderLoaded() || Robot.shooterSubsystem.isBoulderRetracted()){
    		Robot.shooterSubsystem.setIntakeMotorReverse(IntakeReverseSpeed.LOW);
    	}
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return !Robot.shooterSubsystem.isBoulderLoaded() && !Robot.shooterSubsystem.isBoulderRetracted();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopIntakeMotor();
    }

    protected void interrupted() {
    }
}
