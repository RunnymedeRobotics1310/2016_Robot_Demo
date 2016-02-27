package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Field.Defense;
import robot.Field.Goal;
import robot.Field.Lane;
import robot.Field.Slot;
import robot.commands.auto.AutoDriveAndShootCommand;
import robot.utils.R_Extreme3DPro_GameController;
import robot.utils.R_GameController;
import robot.utils.R_GameControllerFactory;
import robot.utils.R_GameController.Axis;
import robot.utils.R_GameController.Button;
import robot.utils.R_GameController.Stick;
import robot.utils.R_GameController.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private enum ButtonMap {

		SHOOT_LOW_GOAL_BUTTON(Button.A),
		SHOOT_HIGH_GOAL_BUTTON(Button.BUTTON1),
		SETUP_HIGH_SHOT_BUTTON(Button.BUTTON2),
		TURBO_BUTTON(Button.LEFT_BUMPER),
		CANCEL_COMMAND_BUTTON(Button.X),
		ROLL_INTAKE_BUTTON(Button.RIGHT_BUMPER),
		ARM_PID_TOGGLE(Button.BUTTON7),
		RESET_ARM_ENCODER_BUTTON(Button.BUTTON8),
		DRIVE_ARM_POS_1(Button.BUTTON9),
		DRIVE_ARM_POS_2(Button.BUTTON10),
		DRIVE_ARM_POS_3(Button.BUTTON11), 
		DRIVE_ARM_POS_4(Button.BUTTON12);

		private Button button;

		ButtonMap(Button button) {
			this.button = button;
		}

		public Button getButton() {
			return this.button;
		}

	}

	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private R_Extreme3DPro_GameController operatorStick = new R_Extreme3DPro_GameController(1);
	
	private AutoChooser autoChooser = new AutoChooser();

	public double getSpeed() {
		double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
		return Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getShootSpeed() {
		double stickValue = operatorStick.getAxis(Axis.SLIDER);
		return Math.round(stickValue * Math.abs(stickValue) * 100) / 100.0;
	}

	public double getShootSpeedOverRideButton() {
		return operatorStick.getAxis(Axis.SLIDER);
	}

	/**
	 * Sets the joystick rumble strength.
	 * 
	 * @param strength
	 *            Has to be between 0.0 and 1.0.
	 */
	public void setRumble(double strength) {
		driverStick.setRumble(strength);
	}

	/**
	 * Sets the joystick rumble strength on the left and right channels
	 * individually.
	 * 
	 * @param leftRumble
	 *            Rumble strength on the left channel.
	 * @param rightRumble
	 *            Rumble strength on the right channel.
	 */
	public void setRumble(double leftRumble, double rightRumble) {
		driverStick.setRumble(leftRumble, rightRumble);
	}

	public int getPOVAngle() {
		return driverStick.getPOVAngle();
	}

	public boolean getGyroReset() {
		return driverStick.getButton(Button.BACK);
	}

	public boolean getGyroCalibrate() {
		return driverStick.getButton(Button.START);
	}

	public boolean getTurbo() {
		return driverStick.getButton(Trigger.LEFT);
	}

	public Defense getDefense() {
		return Defense.toEnum(autoChooser.getSelectedDefence());
	}

	public Slot getSlot() {
		return Slot.toEnum(autoChooser.getSelectedSlot());
	}

	public boolean getIntakeStartButton() {
		return driverStick.getButton(ButtonMap.ROLL_INTAKE_BUTTON.getButton());
	}

	public boolean getSetupHighShotButton() {
		return operatorStick.getButton(ButtonMap.SETUP_HIGH_SHOT_BUTTON.getButton());
	}

	public boolean getShootHighGoalButton() {
		return operatorStick.getButton(ButtonMap.SHOOT_HIGH_GOAL_BUTTON.getButton());
	}

	public boolean getCancel() {
		return driverStick.getButton(ButtonMap.CANCEL_COMMAND_BUTTON.getButton());
	}

	public boolean getShootLowGoalButton() {
		return driverStick.getButton(ButtonMap.SHOOT_LOW_GOAL_BUTTON.getButton());
	}

	public double getArmSpeed() {
		return operatorStick.getAxis(Axis.Y) * operatorStick.getAxis(Axis.Y) * operatorStick.getAxis(Axis.Y);
	}

	public boolean getArmEncoderReset() {
		return operatorStick.getButton(ButtonMap.RESET_ARM_ENCODER_BUTTON.getButton());
	}

	public boolean getArmPIDToggle() {
		return operatorStick.getButton(ButtonMap.ARM_PID_TOGGLE.getButton());
	}

	public double getArmAngle() {
		if (operatorStick.getButton(ButtonMap.DRIVE_ARM_POS_1.getButton())) {
			return 0.0;
		}
		if (operatorStick.getButton(ButtonMap.DRIVE_ARM_POS_2.getButton())) {
			return 100;
		}
		if (operatorStick.getButton(ButtonMap.DRIVE_ARM_POS_3.getButton())) {
			return 265;
		}
		if (operatorStick.getButton(ButtonMap.DRIVE_ARM_POS_4.getButton())) {
			return 200;
		}

		return -1.0;
	}

	public Lane getLane() {
		return Lane.toEnum(autoChooser.getSelectedDistance());
	}

	public Goal getGoal() {
		return Goal.toEnum(autoChooser.getSelectedGoal());
	}

	public boolean getWinch() {
		return driverStick.getButton(Button.Y);
	}
	
	public Command getAutoCommand() {

		switch (autoChooser.getAutoMode()) {
		case "Do Nothing":
			return null;
		case "Drive and Shoot":
			return new AutoDriveAndShootCommand(getSlot(), getDefense(), getLane(), getGoal());
		default:
			return null;
		}
	}

	/**
	 * Update the periodic running elements of the dashboard
	 * <p>
	 * i.e. all toggle buttons
	 */
	public void periodic() {
	}

	/**
	 * Put any items on the dashboard
	 */
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controllers", driverStick.toString());
		SmartDashboard.putString("Operator Controllers", operatorStick.toString());
	}
}
