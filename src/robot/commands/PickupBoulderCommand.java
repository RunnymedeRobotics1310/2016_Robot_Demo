package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.RobotMap;

public class PickupBoulderCommand extends Command {

    public PickupBoulderCommand() {
       requires(ShooterSubsystem);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	ShooterSubsystem.startIntakeMotor();
    }

    protected boolean isFinished() {
        return ShooterSubsystem.isBoulderLoaded();
    }

    protected void end() {
    	ShooterSubsystem.stopIntakeMotor();
    }

    protected void interrupted() {
    }
}
