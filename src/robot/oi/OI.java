package robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
import robot.utils.R_Extreme3DPro_GameController;
import robot.utils.R_GameController;
import robot.utils.R_GameController.Axis;
import robot.utils.R_GameController.Button;
import robot.utils.R_GameController.Stick;
import robot.utils.R_GameController.Trigger;
import robot.utils.R_GameControllerFactory;

@SuppressWarnings("unused")
public class OI {
	
	public OI() {
	}
	
	
	public enum ButtonMap  {

		//REMOTE 1
	/** Working controls as of FLL Ontario Innovation Celebration:
	 * Left & Right stick: Drive
	 * Back: Resets the gyro (unplugged)
	 * Start: Calibrates the gyro (unplugged)
	 * Left Bumper: Fully intake the ball
	 * Right Bumper: Low goal (outtake) the boulder
	 * Y: Cancels the current command & unjams a stuck ball
	 * A: Spins up the shooter & raises the hood
	 * B: Launches the boulder
	 * Left Trigger: Inner intake, usually done automatically
	 * Right Trigger: Shifts high/low gear
	 **/
		RESET_GYRO(Button.BACK),
		CALIBRATE_GYRO(Button.START),
		SHOOT_BOULDER(Button.B),
		OUTER_INTAKE_BOULDER(Button.LEFT_BUMPER),
		EXTAKE_BOULDER(Button.RIGHT_BUMPER),
		WIND_UP_SHOOTER(Button.A),
		CANCEL_COMMAND(Button.Y),
		
		//REMOTE 2 - Unused
		WIND_UP_BANK_SHOT(Button.BUTTON3),
		AUTO_SHOT_ALIGN(Button.BUTTON4),
		MANUAL_SHOT_ALIGN(Button.BUTTON5),
		ARM_PID_OVERRIDE(Button.BUTTON6),
		ROTATE_ARM_DRIVE_POS(Button.BUTTON11);
		
		
		private Button button;

		ButtonMap(Button button) {
			this.button = button;
		}

		public Button getButton() {
			return this.button;
		}
	}
	
	public enum TriggerMap {
		

		INNER_INTAKE_BOULDER(Trigger.LEFT), SHIFT_GEARS(Trigger.RIGHT);
		
		private Trigger trigger;

		TriggerMap(Trigger trigger) {
			this.trigger = trigger;
		}

		public Trigger getTrigger() {
			return this.trigger;
		}
	}
	
	public enum POVMap {
		UP(0), RIGHT(90), DOWN(180), LEFT(270);
		
		private int angle;
		
		POVMap(int angle) {
			this.angle = angle;
		}
		
		public int getAngle() {
			return this.angle;
		}
	}
	
	public enum Nudge { LEFT, RIGHT, NONE };
	
	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private R_Extreme3DPro_GameController operatorStick = new R_Extreme3DPro_GameController(1);
	
	
	public boolean getAutoAlignShotButton() {
		return operatorStick.getButton(ButtonMap.AUTO_SHOT_ALIGN.getButton());
	}
	
	public boolean getManualAlignShotButton() {
		return operatorStick.getButton(ButtonMap.MANUAL_SHOT_ALIGN.getButton());
	}
	

	public boolean getOuterIntakeBoulderButton() {
		return driverStick.getButton(ButtonMap.OUTER_INTAKE_BOULDER.getButton());
	}
	
	public double getInnerIntakeBoulderButton() {
		return driverStick.getTrigger(TriggerMap.INNER_INTAKE_BOULDER.getTrigger());
	}
	
	public double getGearShiftButton() {
		return driverStick.getTrigger(TriggerMap.SHIFT_GEARS.getTrigger());
	}
	
	public boolean getExtakeBoulderButton() {
		return driverStick.getButton(ButtonMap.EXTAKE_BOULDER.getButton());
	}
	
	public boolean getResetGyroButton() {
		return driverStick.getButton(ButtonMap.RESET_GYRO.getButton());
	}
	
	public boolean getWindUpBankShotButton() {
		return operatorStick.getButton(ButtonMap.WIND_UP_BANK_SHOT.getButton());
	}
	
	public boolean getWindUpShooterButton() {
		return driverStick.getButton(ButtonMap.WIND_UP_SHOOTER.getButton());
	}
	
	public boolean getShootButton() {
		return driverStick.getButton(ButtonMap.SHOOT_BOULDER.getButton());
	}
	
	public boolean getBallStuckButton() {
		return operatorStick.getButton(Button.A);
	}
	
	public boolean getRotateArmDrivePosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_DRIVE_POS.getButton());
	}
	
	public boolean getArmPIDOverride() {
		return operatorStick.getButton(ButtonMap.ARM_PID_OVERRIDE.getButton());
	}
	
	public double getSpeed() {
		double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
		return Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}
	
	public double getPOV() {
		return driverStick.getPOVAngle();
	}
	
	public boolean getRotateLeft() {
		return (getPOV() == POVMap.LEFT.getAngle());
	}
	
	public boolean getRotateRight() {
		return (getPOV() == POVMap.RIGHT.getAngle());
	}

	public double getShootSpeed() {
		double stickValue = operatorStick.getAxis(Axis.SLIDER);
		return Math.round(stickValue * Math.abs(stickValue) * 100) / 100.0;
	}
	
	public boolean getCancel() {
		return driverStick.getButton(ButtonMap.CANCEL_COMMAND.getButton());
	}
	
	public boolean getGyroReset() {
		return driverStick.getButton(ButtonMap.RESET_GYRO.getButton());
	}

	public boolean getGyroCalibrate() {
		return driverStick.getButton(ButtonMap.CALIBRATE_GYRO.getButton());
	}
     
   
    public double getJoystickTargetCenter() {
    	return operatorStick.getAxis(Axis.X);
    }

	/**
	 * Update the periodic running elements of the dashboard
	 * <p>
	 * i.e. all toggle buttons
	 */
	public void periodic() {
		if (Math.abs(operatorStick.getAxis(Axis.X)) < 0.5) {
		}
	}

	/**
	 * Put any items on the dashboard
	 */
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controllers", driverStick.toString());
		SmartDashboard.putString("Operator Controllers", operatorStick.toString());
		SmartDashboard.putNumber("X-Operator", operatorStick.getAxis(Axis.X));
	}
	
}
