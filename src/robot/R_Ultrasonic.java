package robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class R_Ultrasonic extends AnalogInput {
	R_LowPassFilter lowPass = new R_LowPassFilter(10, 0.02, 10);
	
	public R_Ultrasonic(int port) {
		super(port);
		reset();
	}
	
	/**
	 * Resets the lowpass filter.
	 * 
	 * Must be called before getDistance();
	 */
	public void reset() {
		lowPass.reset(getRawDistance());
	}

	/**
	 * Gets the distance from the ultrasonic sensor.
	 * 
	 * @return Distance in inches
	 */
	public double getRawDistance() {
		// y = mx + b (old) v = 0.0094d + 0.01
		// y = mx + b v = 0.0094d + 0.0393
		// (y - b)/m = x
		return (super.getVoltage() - 0.0393) / 0.0094;
	}
	
	/**
	 * Gets the distance from the ultrasonic sensor and then filters it. 
	 * 
	 * @return Filtered distance in inches
	 */
	public double getDistance() {
		return lowPass.calculate(getRawDistance());
	}
}
