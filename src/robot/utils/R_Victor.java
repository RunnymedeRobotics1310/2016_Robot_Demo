package robot.utils;

import edu.wpi.first.wpilibj.VictorSP;
import robot.RobotMap.MotorMap;

/**
 * This class is a "driver" for VEX Robotics Victors. Victors are speed
 * controllers.
 */
public class R_Victor extends VictorSP {

	public R_Victor(MotorMap motor) {
		super(motor.port);
		this.setInverted(motor.inverted);
	}
}
