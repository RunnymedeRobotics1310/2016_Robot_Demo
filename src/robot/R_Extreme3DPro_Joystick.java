package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_Extreme3DPro_Joystick {
	private final Joystick joystick;

	public enum Axis {
		X, Y, Z;
	}

	public enum JoystickButton {
		BUTTON1,
		BUTTON2,
		BUTTON3,
		BUTTON4,
		BUTTON5,
		BUTTON6,
		BUTTON7,
		BUTTON8,
		BUTTON9,
		BUTTON10,
		BUTTON11,
		BUTTON12;
	}
	
	public R_Extreme3DPro_Joystick(int port) {
		this.joystick = new Joystick(port);
	}

	public R_Extreme3DPro_Joystick(Joystick j) {
		this.joystick = j;
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
		}

		// Round the axis value to 2 decimal places
		return Math.round(axisValue * 100) / 100.0;
	}

	public boolean getButton(JoystickButton button) {
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
		default:
			return false;
		}
	}

	public int getPOVAngle() {
		return joystick.getPOV();
	}

	public Joystick getRawJoystick() {
		return joystick;
	}
}