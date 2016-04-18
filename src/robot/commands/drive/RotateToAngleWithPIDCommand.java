package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;
import robot.pids.RotateToAnglePID;

/**
 *
 */
public class RotateToAngleWithPIDCommand extends Command {

	double angleSetpoint;

	public RotateToAngleWithPIDCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RotateToAnglePID.setEnabled(false);
		RotateToAnglePID.setSetpoint(angleSetpoint);
		RotateToAnglePID.setEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftSpeed;
		double rightSpeed;

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("AnglePIDOutput", RotateToAnglePID.getOutput());

		double turn = RotateToAnglePID.getOutput();
		
		if(turn > 0) {
			leftSpeed = turn;
			rightSpeed = -turn;
		}
		else if (turn < 0) {
			leftSpeed = turn;
			rightSpeed = -turn;
		}
		else {
			leftSpeed = 0;
			rightSpeed = 0;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double error =  -Robot.chassisSubsystem.getAngleDifference(angleSetpoint);
		if (Math.abs(error) < 0.10) {
			return true;
		}
		if (Robot.oi.getNoLongerAlignShotButton()) { return true; }
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RotateToAnglePID.setEnabled(false);
		/*
		 * Note: added motor stop incase it's not called by a separate command
		 * as in AutoGoStraightCommand calling GoStraightCommand
		 */
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
