package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.R_GameController.Button;
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
        return Robot.shooterSubsystem.isBoulderLoaded() || Robot.oi.getButton(Button.X);
    }

    protected void end() {
    	Robot.shooterSubsystem.stopIntakeMotor();
    }

    protected void interrupted() {
    }
}
