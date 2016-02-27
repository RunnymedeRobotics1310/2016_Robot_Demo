package robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    private final static boolean inverted = true;

    public final static double ANALOG_OFFSET = 315;
    
    public enum ArmLevel {
    	INTAKE_LEVEL(60.0),
    	SHOOT_LEVEL(215.0),
    	DRIVE_LEVEL(245.0);
    	
    	public final double angle;
    	
    	ArmLevel(double angle) {
    		this.angle = angle;
    	}
    	
    	public double getAngle() {
    		return this.angle;
    	}
    }
    public enum MotorMap {
        LEFT_MOTOR      (0,  RobotMap.inverted),
        RIGHT_MOTOR     (1, !RobotMap.inverted),
        INTAKE_MOTOR    (2,  RobotMap.inverted),
        SHOOTER_MOTOR   (3,  RobotMap.inverted),
        ARM_DEPLOY_MOTOR(4, !RobotMap.inverted),
        ARM_INTAKE_MOTOR(5, !RobotMap.inverted),
        LEFT_WINCH      (7,  RobotMap.inverted),
        RIGHT_WINCH     (6, !RobotMap.inverted);

        public final int port;
        public final boolean inverted;

        MotorMap(int port, boolean invertedState) {
            this.port = port;
            this.inverted = invertedState;
        }
    }
    
    public enum SensorMap {
    	// Analog Ports
    	GYRO                      (0),
    	ULTRASONIC                (1),
    	ANGLE_ENCODER             (2),
    	
    	// Digital Ports
    	SHOOTER_SPEED_ENCODER     (6),
    	LEFT_PROXIMITY_SENSOR     (7),
    	RIGHT_PROXIMITY_SENSOR    (8),
    	UPPER_PROXIMITY_SENSOR    (9),
    	BOULDER_PROXIMITY_SENSOR  (10),
    	ARM_LOWER_LIMIT           (11),
    	ARM_UPPER_LIMIT           (12);
    	
        public final int port;

        SensorMap(int port) {
            this.port = port;
        }
    }
    
    public enum EncoderMap {
    	LEFT (0, 1, 9000.0, 22000.0, 96.84, !RobotMap.inverted),
    	RIGHT(2, 3, 9000.0, 22000.0, 96.84,  RobotMap.inverted),
    	//FIXME: FIX THE LOW AND HIGH GEAR SPEED.
    	INTAKE_ENCODER(4, 5, 1800.0, 1800.0, 29.75, RobotMap.inverted);
    	//Counts per inch not accurate
    	
    	public final int ch1;
    	public final int ch2;
    	public final double maxRate;
    	public final double maxRateHigh;
    	public final double countsPerInch;
    	public final boolean inverted;
    	
    	EncoderMap(int ch1, int ch2, double maxRate, double maxRateHigh, double countsPerInch,
    			boolean inverted) {
    		this.ch1 = ch1;
    		this.ch2 = ch2;
    		this.maxRate = maxRate;
    		this.maxRateHigh = maxRateHigh;
    		this.countsPerInch = countsPerInch;
    		this.inverted = inverted;
    	}
    }
    
    public enum Pneumatics{
    	BALLSHIFTER_LOW   (0),
    	BALLSHIFTER_HIGH  (1),
    	SHOOTER_RAIL_DOWN (2),
    	SHOOTER_RAIL_UP   (3),
    	SCISSOR_LIFT_1    (4),
    	SCISSOR_LIFT_2    (5),
    	SCISSOR_LIFT_3    (6),
    	SCISSOR_LIFT_4    (7);
    	
       	//Pneumatics control module port
    	public final int pcmPort;
 
    	Pneumatics (int pcmPort){
    		this.pcmPort = pcmPort;
    	} 
    }
}
