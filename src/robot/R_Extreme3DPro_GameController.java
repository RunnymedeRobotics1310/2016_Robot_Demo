package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_Extreme3DPro_GameController {
	
	private final Joystick joystick;

	public R_Extreme3DPro_GameController(int port) {
		joystick = new Joystick(port);
	}

	public R_Extreme3DPro_GameController(Joystick j) {
		joystick = j;
	}

	public double getRawAxis(int axis) {

		double axisValue = 0.0;
			switch (axis) {
				case 0:  axisValue = joystick.getRawAxis(0);  break;
				case 1:  axisValue = joystick.getRawAxis(1);  break;
			}
		// Round the axis value to 2 decimal places
		return Math.round(axisValue*100) / 100.0;
	}

	public boolean getRawButton(int button) {
		switch (button) {
			case 1:     return joystick.getRawButton(1);
			case 2:		return joystick.getRawButton(2);
			case 3:		return joystick.getRawButton(3);
			case 4:		return joystick.getRawButton(4);
			case 5:		return joystick.getRawButton(5);
			case 6:		return joystick.getRawButton(6);
			case 7:		return joystick.getRawButton(7);
			case 8:		return joystick.getRawButton(8);
			case 9:		return joystick.getRawButton(9);
			case 10:	return joystick.getRawButton(10);
			case 11:	return joystick.getRawButton(11);
			case 12:	return joystick.getRawButton(12);
			default:	return false;
		}
	}
	
	public Joystick getRawJoystick() { return joystick; }

}
