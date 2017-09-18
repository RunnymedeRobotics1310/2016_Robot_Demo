package robot.utils;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class will attempt to detect the controller(s) plugged into the
 * DriverStatation and assign the proper {@link R_GameController}.
 */
public class R_GameControllerFactory {

	public static R_GameController getGameController(int port) {

		Joystick newStick = new Joystick(port);

		switch (newStick.getName()) {
		case "Controller (XBOX 360 For Windows)":
			return new R_Xbox_GameController(newStick);
		case "Controller (Xbox One For Windows)":
			return new R_Xbox_GameController(newStick);
		/*
		 * case "Logitech Extreme 3D": return new
		 * R_Extreme3DPro_GameController(newStick);
		 */
		case "Gamepad F310":
			return new R_F310_GameController(newStick);
		default:
			return new R_F310_GameController(newStick);
		}
	}

}