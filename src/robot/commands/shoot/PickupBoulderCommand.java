package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class PickupBoulderCommand extends Command {

    public PickupBoulderCommand() {
       requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Robot.shooterSubsystem.startIntakeMotor();
    }

    protected boolean isFinished() {
        return Robot.shooterSubsystem.isBoulderLoaded();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopIntakeMotor();
    }

    protected void interrupted() {
    }
}
