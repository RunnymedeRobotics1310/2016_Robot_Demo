package robot;

import edu.wpi.first.wpilibj.DigitalInput;
import robot.RobotMap.MotorMap;

public class R_SafetyVictor extends R_Victor {
	
	private DigitalInput positiveLimitSwitch = null;
	private DigitalInput negativeLimitSwitch = null;
	
	public R_SafetyVictor(MotorMap motor, DigitalInput positiveLimitSwitch, DigitalInput negativeLimitSwitch) {
		super(motor);
		this.setInverted(motor.inverted);
		this.positiveLimitSwitch = positiveLimitSwitch;
		this.negativeLimitSwitch = negativeLimitSwitch;
	}
	
	public void set(double speed) {
		
		// The speed must be between -1.0 and 1.0
		if (speed >  1.0) { speed =  1.0; }
		if (speed < -1.0) { speed = -1.0; }

		// Check the forward speed if at the limit
		// The limit switch is at the limit if 
		if (positiveLimitSwitch != null) {
			if (speed > 0.0 && !positiveLimitSwitch.get()) {
				speed = 0.0;
			}
		}
		
		if (negativeLimitSwitch != null) {
			if (speed < 0.0 && !negativeLimitSwitch.get()) {
				speed = 0.0;
			}
		}
		
		super.set(speed);
		
	}
	
}
