package robot;

import edu.wpi.first.wpilibj.Joystick;
import robot.R_GameController.Axis;
import robot.R_GameController.Button;

public class R_Extreme3DPro_GameController {

	private final Joystick joystick;

	public R_Extreme3DPro_GameController(int port) {
		joystick = new Joystick(port);
	}

	public R_Extreme3DPro_GameController(Joystick j) {
		joystick = j;
	}

	public double getAxis(Axis axis) {

		double axisValue = 0.0;
		switch (axis) {
		case X:
			axisValue = joystick.getRawAxis(0);
			break;
		case Y:
			axisValue = joystick.getRawAxis(1);
			break;
		case Z:
			axisValue = joystick.getRawAxis(2);
		case SLIDER:
			axisValue = joystick.getRawAxis(3);
		}
		// Round the axis value to 2 decimal places
		return Math.round(axisValue * 100) / 100.0;
	}

	public boolean getButton(Button button) {
		switch (button) {
		case BUTTON1:
			return joystick.getRawButton(1);
		case BUTTON2:
			return joystick.getRawButton(2);
		case BUTTON3:
			return joystick.getRawButton(3);
		case BUTTON4:
			return joystick.getRawButton(4);
		case BUTTON5:
			return joystick.getRawButton(5);
		case BUTTON6:
			return joystick.getRawButton(6);
		case BUTTON7:
			return joystick.getRawButton(7);
		case BUTTON8:
			return joystick.getRawButton(8);
		case BUTTON9:
			return joystick.getRawButton(9);
		case BUTTON10:
			return joystick.getRawButton(10);
		case BUTTON11:
			return joystick.getRawButton(11);
		case BUTTON12:
			return joystick.getRawButton(12);
		default:
			return false;
		}
	}

	public Joystick getRawJoystick() {
		return joystick;
	}

}
