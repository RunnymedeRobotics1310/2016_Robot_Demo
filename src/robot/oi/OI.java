package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Field.Defense;
import robot.Field.Goal;
import robot.Field.Lane;
import robot.Field.Slot;
import robot.R_Extreme3DPro_GameController;
import robot.R_GameController;
import robot.R_GameController.Axis;
import robot.R_GameController.Button;
import robot.R_GameController.Stick;
import robot.R_GameController.Trigger;
import robot.R_GameControllerFactory;
import robot.commands.auto.AutoDriveAndShootCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private enum ButtonMap {

		SHOOT_LOW_GOAL_BUTTON(Button.A), SHOOT_HIGH_GOAL_BUTTON(Button.B), TURBO_BUTTON(
				Button.LEFT_BUMPER), CANCEL_COMMAND_BUTTON(Button.X), ROLL_INTAKE_BUTTON(
						Button.RIGHT_BUMPER), TOGGLE_ARM_BUTTON(Button.Y);

		private Button button;

		ButtonMap(Button button) {
			this.button = button;
		}

		public Button getButton() {
			return this.button;
		}

	}

	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private AutoChooser autoChooser = new AutoChooser();

	private R_Extreme3DPro_GameController operatorStick = new R_Extreme3DPro_GameController(1);

	public double getSpeed() {
		double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
		return Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
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

	public boolean getShootHighGoalButton() {
		return driverStick.getButton(ButtonMap.SHOOT_HIGH_GOAL_BUTTON.getButton());
	}

	public boolean getCancel() {
		return driverStick.getButton(ButtonMap.CANCEL_COMMAND_BUTTON.getButton());
	}

	public boolean getShootLowGoalButton() {
		return driverStick.getButton(ButtonMap.SHOOT_LOW_GOAL_BUTTON.getButton());
	}

	public boolean getArmDeploy() {
		return driverStick.getButton(ButtonMap.TOGGLE_ARM_BUTTON.getButton());
	}

	public double getArmSpeed() {
		return operatorStick.getRawAxis(1) * operatorStick.getRawAxis(1)
				* operatorStick.getRawAxis(1); //Y-axis
	}

	public boolean getArmEncoderReset() {
		return operatorStick.getRawButton(8);
	}

	public boolean getArmPIDDisable() {
		return operatorStick.getRawButton(7);
	}

	public double getArmAngle() {
		if (operatorStick.getRawButton(9)) {
			return 0.0;
		}
		if (operatorStick.getRawButton(10)) {
			return 100;
		}
		if (operatorStick.getRawButton(11)) {
			return 200;
		}
		if (operatorStick.getRawButton(12)) {
			return 300;
		}
		
		return -1.0;
	}

	public Lane getLane() {
		return Lane.toEnum(autoChooser.getSelectedDistance());
	}

	public Goal getGoal() {
		return Goal.toEnum(autoChooser.getSelectedGoal());
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
