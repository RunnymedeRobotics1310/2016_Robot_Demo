package robot.utils;

import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * This class is a "driver" for the gyro. This class constrains the angle
 * measured to 360 degrees.
 */
public class R_Gyro extends AnalogGyro {

	public R_Gyro(int port) {
		super(port);
	}

	@Override
	public double getAngle() {
		double angle = -super.getAngle() % 360;
		return (angle < 0) ? angle + 360 : angle;
	}

	public double getAngleDifference(double targetAngle) {
		double currentAngle = getAngle();
		double difference = targetAngle - currentAngle;

		if (difference > 180) {
			difference -= 360;
		} else if (difference < -180) {
			difference += 360;
		}

		return difference;
	}

}
