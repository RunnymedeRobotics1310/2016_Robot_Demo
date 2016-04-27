package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.ChassisSubsystem.Gear;

/**
 * This command is used for shifting gears. Low gear makes the robot a "tank".
 * High gear gives the robot amazing mobility. 
 */
public class ShiftGearCommand extends Command {

	private final Gear GEAR;

	public ShiftGearCommand(Gear gear) {
		this.GEAR = gear;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected void initialize() {
		Robot.chassisSubsystem.setGear(GEAR);
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
