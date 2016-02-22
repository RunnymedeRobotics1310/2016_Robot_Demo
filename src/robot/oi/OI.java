package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Field.Defense;
import robot.Field.Goal;
import robot.Field.Lane;
import robot.Field.Slot;
import robot.R_Extreme3DPro_Joystick;
import robot.R_Extreme3DPro_Joystick.JoystickButton;
import robot.R_Gamepad;
import robot.R_Gamepad.GamepadAxis;
import robot.R_Gamepad.GamepadButton;
import robot.R_Gamepad.GamepadStick;
import robot.R_Gamepad.GamepadTrigger;
import robot.R_GamepadFactory;
import robot.commands.auto.AutoDriveAndShootCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private R_Gamepad driverStick = R_GamepadFactory.getGameController(0);
	private R_Extreme3DPro_Joystick operatorStick = new R_Extreme3DPro_Joystick(1);
	private AutoChooser autoChooser = new AutoChooser();

	public double getSpeed() {
		double joystickValue = driverStick.getAxis(GamepadStick.LEFT, GamepadAxis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(GamepadStick.RIGHT, GamepadAxis.X);
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
		return driverStick.getButton(GamepadButton.BACK);
	}

	public boolean getGyroCalibrate() {
		return driverStick.getButton(GamepadButton.START);
	}

	public boolean getTurbo() {
		return driverStick.getButton(GamepadTrigger.LEFT);
	}

	public Defense getDefense() {
		return Defense.toEnum(autoChooser.getSelectedDefence());
	}

	public Slot getSlot() {
		return Slot.toEnum(autoChooser.getSelectedSlot());
	}

	public boolean getIntakeStartButton() {
		return driverStick.getButton(GamepadButton.RIGHT_BUMPER);
	}

	public boolean getShootHighGoalButton() {
		return driverStick.getButton(GamepadButton.B);
	}

	public boolean getCancel() {
		return driverStick.getButton(GamepadButton.X);
	}

	public boolean getShootLowGoalButton() {
		return driverStick.getButton(GamepadButton.A);
	}

	public boolean getArmDeploy() {
		return driverStick.getButton(GamepadTrigger.RIGHT);
	}

	public Lane getLane() {
		return Lane.toEnum(autoChooser.getSelectedDistance());
	}

	public Goal getGoal() {
		return Goal.toEnum(autoChooser.getSelectedGoal());
	}

	public boolean portCullisLowBarButton() {
		return operatorStick.getButton(JoystickButton.BUTTON12);
	}

	public boolean chavelDeFriseButton() {
		return operatorStick.getButton(JoystickButton.BUTTON11);
	}

	// TODO: Make a seperate reverse rollers function - Slower than low goal
	// button?
	public boolean getReverseRollers() {
		return driverStick.getButton(GamepadButton.Y);
	}

	// TODO: Press once to raise the hanger, press again to winch?
	public boolean getHangButton() {
		return operatorStick.getButton(JoystickButton.BUTTON3);
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
		SmartDashboard.putString("Driver Controller", driverStick.toString());
		SmartDashboard.putString("Operator controller", operatorStick.toString());
	}
}
