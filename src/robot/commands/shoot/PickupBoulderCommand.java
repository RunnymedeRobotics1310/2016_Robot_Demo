package robot.commands.shoot;

import edu.wpi.first.wpilibj.command.Command;
import robot.R_GameController.Button;
import robot.Robot;

public class PickupBoulderCommand extends Command {

	boolean timerSet = false;
	
    public PickupBoulderCommand() {
       requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    	timerSet = false;
    }

    protected void execute() {
    	Robot.shooterSubsystem.startIntakeMotor();
    }

    protected boolean isFinished() {
    	if (Robot.shooterSubsystem.isBoulderLoaded() && !timerSet) {
    		this.setTimeout(this.timeSinceInitialized() + 0.25);
    		timerSet = true;
    	}
        return this.isTimedOut() || Robot.oi.getCancel();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopIntakeMotor();
    }

    protected void interrupted() {
    }
}
