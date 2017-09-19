package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;
import robot.RobotMap;
import robot.pids.RotateToAnglePID;

/**
 * This command is used for rotating the robot to a vision target. It will
 * rotate the robot until the vision target is at the center of the camera's
 * FOV.
 */
public class RotateToAngleWithPIDCommand extends Command {

	double angleSetpoint;
	TargetingMode targetingMode;
	
	MatchPeriod period;

	public RotateToAngleWithPIDCommand(MatchPeriod period) {
		requires(Robot.chassisSubsystem);
		
		this.period = period;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		if (period == MatchPeriod.TELEOP) {
			this.targetingMode = Robot.oi.getVisionTargetCenter() == RobotMap.NO_VISION_TARGET ? TargetingMode.JOYSTICK : TargetingMode.VISION;	
		} else {
			this.targetingMode = Robot.oi.getVisionTargetCenter() == RobotMap.NO_VISION_TARGET ? TargetingMode.AUTO : TargetingMode.VISION;
		}
		double pixelDifference;
		
		
		switch (targetingMode) {
		case AUTO:
			return;
		case JOYSTICK:
			pixelDifference = Robot.oi.getJoystickTargetCenter() - 102.0;
			break;
		case VISION:
			pixelDifference = Robot.oi.getVisionTargetCenter() - 102.0;
			break;
		default:
			pixelDifference = 0.0;
		}
		
		double angleDifference = pixelDifference * RobotMap.DEGREES_PER_PIXEL;

		this.angleSetpoint = Robot.chassisSubsystem.getCurrentAngle() + angleDifference;

		System.out.println("Target Locking - " + "Current Angle: " + Robot.chassisSubsystem.getCurrentAngle()
				+ " Pixel Difference: " + pixelDifference + " Angle Difference: " + angleDifference);

		RotateToAnglePID.setEnabled(false);
		RotateToAnglePID.setSetpoint(angleSetpoint);
		Robot.chassisSubsystem.startAutoTargeting();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftSpeed;
		double rightSpeed;

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("AnglePIDOutput", RotateToAnglePID.getOutput());

		double angleDifference = -Robot.chassisSubsystem.getAngleDifference(angleSetpoint);
		double turn = 0.0;
		if (Math.abs(angleDifference) < 10.0) {
			RotateToAnglePID.setEnabled(true);
		}

		if (RotateToAnglePID.isEnabled()) {
			turn = RotateToAnglePID.getOutput();
		} else {
			turn = -0.3 * Math.signum(angleDifference);
		}

		if (turn > 0.0) {
			leftSpeed = turn;
			rightSpeed = -turn;
		} else if (turn < 0.0) {
			leftSpeed = turn;
			rightSpeed = -turn;
		} else {
			leftSpeed = 0.0;
			rightSpeed = 0.0;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		if (!Robot.chassisSubsystem.isAutoTargeting()) { return true; }
		
		if (targetingMode == TargetingMode.AUTO) {return true;}
		
		double error = -Robot.chassisSubsystem.getAngleDifference(angleSetpoint);
		if (Math.abs(error) < 0.10 && Math.abs(Robot.chassisSubsystem.getAngleRate()) < 1.0) {
			return true;
		}
		if(Robot.oi.getCancel()) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		
		System.out.println("Finished Target Lock");
		
		RotateToAnglePID.setEnabled(false);
		/*
		 * Note: added motor stop incase it's not called by a separate command
		 * as in AutoGoStraightCommand calling GoStraightCommand
		 */
		Robot.chassisSubsystem.setSpeed(0.0, 0.0);
		Robot.chassisSubsystem.endAutoTargeting();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
