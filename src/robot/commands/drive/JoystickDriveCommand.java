
package robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;
import robot.RobotMap;
import robot.commands.drive.RotateFixedCommand.Direction;
import robot.commands.shoot.AlignAndShootHighShotCommand;
import robot.oi.OI.Nudge;
import robot.subsystems.ChassisSubsystem.Gear;

public class JoystickDriveCommand extends Command {

	private static final double IMAGE_CENTER      = RobotMap.CAMERA_PIXEL_WIDTH / 2.0;
	//Original: 47.0
	private static final double DEGREES_PER_PIXEL = 0.3074;
	//0.196875

	public JoystickDriveCommand() {
		requires(Robot.chassisSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double speed = Robot.oi.getSpeed();
		double turn = Robot.oi.getTurn();
		double leftSpeed;
		double rightSpeed;

		if (Robot.oi.getGyroReset()) {
			Robot.chassisSubsystem.resetGyroHeading();
		}
		
		if (Robot.oi.getGyroCalibrate()) {
			Robot.chassisSubsystem.calibrateGyro();
		}

		/**
		 * If the chassisSubsystem says that we should be in high gear, switch
		 * to high gear, otherwise, switch to low gear.
		 */
		if (Robot.oi.getGearShiftButton() >= 0.5) {
			Robot.chassisSubsystem.setGear(Gear.HIGH);
		} else {
			Robot.chassisSubsystem.setGear(Gear.LOW);
		}
		
		// Nudge the robot to the left or right based on the operator input.
		// If the driver is turning the robot then ignore the nudge command.
		// Pass the speed of the robot to the nudge command.
		Nudge nudge = Robot.oi.getNudge();
		if (Math.abs(turn) < 0.03 && nudge != Nudge.NONE) {
			if (Robot.oi.getNudge() == Nudge.LEFT) {
				Scheduler.getInstance().add(new RotateFixedCommand(Direction.LEFT));
			} else {
			Scheduler.getInstance().add(new RotateFixedCommand(Direction.RIGHT));
			}
		}
		
		/**
		 * If the user is not turning, then follow the gyro using the GoStraight
		 * command.
		 */ 
		/*if (Math.abs(turn) < 0.03) { 
			Scheduler.getInstance().add(new GoStraightCommand(Robot.chassisSubsystem.getCurrentAngle())); 
			return;
		}*/
		
		/*double defaultValue = 0;
		Robot.chassisSubsystem.resetGyroHeading();
		double angle = Robot.oi.table.getNumber("angle", defaultValue);
		System.out.println(angle);
		
		if(Robot.oi.getAlignShotButton()) {
			Scheduler.getInstance().add(new PivotToAngleCommand(angle));
			return;
		}*/
		

		double targetCenterX = Robot.oi.getVisionTargetCenter();
		double pixelDifference = targetCenterX - 98.0;
		double angleDifference = pixelDifference * DEGREES_PER_PIXEL;
		
		if(Robot.oi.getAlignShotButton() && targetCenterX != RobotMap.NO_VISION_TARGET) {
			System.out.println("Pivoting" + " PxDiff " + pixelDifference + " angle: " + Robot.chassisSubsystem.getCurrentAngle() + " angleDif: " + angleDifference);
			Scheduler.getInstance().add(new AlignAndShootHighShotCommand(angleDifference));
			return;
		}
		 
		// Use the driver input to set the speed and direction.
		if (Math.abs(speed) < 0.03) {
			leftSpeed  =   turn / 2.0;
			rightSpeed = - turn / 2.0;
		} else {
			leftSpeed  = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn < 0) ? speed              : speed * (1 - turn) ;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
