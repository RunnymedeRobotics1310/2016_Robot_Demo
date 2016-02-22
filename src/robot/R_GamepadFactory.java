package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_GamepadFactory {

	public static R_Gamepad getGameController(int port) {

		Joystick newStick = new Joystick(port);
		// Dirty, dirty hack
		if (newStick.getIsXbox()){
			return new R_Xbox_Gamepad(port);
		}
		else{
			return new R_F310_Gamepad(newStick);
		}
	}
}