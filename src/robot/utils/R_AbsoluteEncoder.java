package robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class R_AbsoluteEncoder extends AnalogInput {

	private static double MAX_VOLTAGE = 4.92;
	private static double MIN_VOLTAGE = 0.01;
	
	double offset_degrees = 0.0;
	
	public R_AbsoluteEncoder(int port, double offset) {
		super(port);
		this.offset_degrees = offset;
	}
	
	/**
	 * Gets the angle from the the absolute encoder.
	 */
	public double getAngle() {
		double angle = getRawAngle() - offset_degrees;
		if (angle > 360.0) {
			angle -= 360.0;
		}
		if (angle < 0) {
			angle += 360.0;
		}
		
		return angle;
	}
	
	/**
	 * Reset the encoder angle
	 */
	public void reset() {
		// Record the current raw angle as the offset.
		offset_degrees = getRawAngle();
	}

	private double getRawAngle() {
		
		// The raw angle is the angle between 0 and 360 as measured by the encoder voltage
		return (getVoltage() - MIN_VOLTAGE) * 360.0 / (MAX_VOLTAGE - MIN_VOLTAGE);
	}
}
