package robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    private final static boolean inverted = true;

    public final static double ANALOG_OFFSET = 310.0;
    
    /**
     * Number of pixels per row (width part of the camera's resolution).
     */
    public final static double CAMERA_PIXEL_WIDTH = 320.0;
    /**
     * Constant indicating that a target couldn't be found.
     */
    public final static double NO_VISION_TARGET = -1.0;
    /**
     * Number of degrees in each camera pixel. (Horizontal FOV of the camera).
     */
    public final static double DEGREES_PER_PIXEL = 0.22;
    
    
    /**
     *	Enum containing all predetermined levels for the arm.
     * <li>{@link #GROUND_LEVEL}</li>
     * <li>{@link #LOWER_LIMIT}</li>
     * <li>{@link #LOW_LEVEL}</li>
     * <li>{@link #INTAKE_LEVEL}</li>
     * <li>{@link #PORTCULLIS_LEVEL}</li>
     * <li>{@link #SHOOT_LEVEL}</li>
     * <li>{@link #DRIVE_LEVEL}</li>
     * <li>{@link #BANK_SHOT_LEVEL}</li>
     */
    public enum ArmLevel {
    	/**
    	 * This level is set to be 70.0 degrees.
    	 */
    	GROUND_LEVEL(70.0),
    	/**
    	 * This level is set to be 95.0 degrees. The physical location of the
    	 * lower hall effect sensor.
    	 */
    	LOWER_LIMIT(95.0),
    	/**
    	 * This level is set to be 125.0 degrees, the lowest we allow the robot
    	 * to be manually set to.
    	 */
    	LOW_LEVEL(125.0),
    	/**
    	 * This level is set to be 135.0 degrees. This is the level used for
    	 * intaking.
    	 */
    	INTAKE_LEVEL(135.0),
    	/**
    	 * This level is set to be 165.0 degrees. This level easily lifts the
    	 * port cullis.
    	 */
    	PORTCULLIS_LEVEL(165.0),
    	/**
    	 * This level is set to be 225.0 degrees. This level puts the arm at a
    	 * position where it doesn't interfere with the high goal shooter.
    	 */
    	SHOOT_LEVEL(225.0),
    	/**
    	 * This level is set to be 300.0 degrees. This level puts the arm at a
    	 * position where it is safe from crashing onto other things while
    	 * driving.
    	 */
    	DRIVE_LEVEL(300.0),
    	/**
    	 * This level is set to be 328.0 degrees. This level puts the arm at a
    	 * position where a boulder can be bounced off the arm while climbing
    	 * in order to make a last-minute high goal.
    	 */
    	BANK_SHOT_LEVEL(328.0),
    	/**
    	 * This level is set to be 350.0 degrees. The physical location of the
    	 * upper hall effect sensor.
    	 */
    	UPPER_LIMIT(355.0);
    	
    	public final double angle;
    	
    	ArmLevel(double angle) {
    		this.angle = angle;
    	}
    	
    	/**
    	 * 
    	 * @return Angle held by this enum constant.
    	 */
    	public double getAngle() {
    		return this.angle;
    	}
    }
    
    /**
     *	Enum containing a list of all the motors in the robot.
     */
    public enum MotorMap {
        LEFT_MOTOR      (0,  RobotMap.inverted),
        RIGHT_MOTOR     (1, !RobotMap.inverted),
        INTAKE_MOTOR    (2,  RobotMap.inverted),
        SHOOTER_MOTOR   (3,  RobotMap.inverted),
        ARM_DEPLOY_MOTOR(4, !RobotMap.inverted),
        ARM_INTAKE_MOTOR(5, !RobotMap.inverted),
        LEFT_WINCH      (7,  RobotMap.inverted),
        RIGHT_WINCH     (6, !RobotMap.inverted);

    	/**
    	 * Port the motor's speed controller is plugged into.
    	 */
        public final int port;
        /**
         * Boolean indicating whether the motor is inverted.
         */
        public final boolean inverted;
        
        MotorMap(int port, boolean invertedState) {
            this.port = port;
            this.inverted = invertedState;
        }
    }
    
    /**
     * Enum containing a list of all the sensors on the robot.
     */
    public enum SensorMap {
    	// Analog Ports
    	GYRO                      (0),
    	REAR_ULTRASONIC           (1),
    	ANGLE_ENCODER             (2),
    	FRONT_ULTRASONIC		  (3),
    	
    	// Digital Ports
    	SHOOTER_SPEED_ENCODER     (9),
    	LEFT_PROXIMITY_SENSOR     (7),
    	RIGHT_PROXIMITY_SENSOR    (6),
    	UPPER_PROXIMITY_SENSOR    (10),
    	BOULDER_PROXIMITY_SENSOR  (8),
    	ARM_LOWER_LIMIT           (12),
    	ARM_UPPER_LIMIT           (11);
    	
        public final int port;

        SensorMap(int port) {
            this.port = port;
        }
    }
    
    /**
     * Enum containing a list of all the encoders on the robot.
     */
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
    
    /**
     * Enum containing a list of all the components of the pneumatic system.
     */
    public enum Pneumatics{
    	BALLSHIFTER   			(0),
    	SHOOTER_RAIL 			(2),
    	SCISSOR_LIFT_RELEASE    (3),
    	SCISSOR_LIFT_EXTEND1    (4),
    	SCISSOR_LIFT_RETRACT1   (5),
    	SCISSOR_LIFT_EXTEND2    (6),
    	SCISSOR_LIFT_RETRACT2   (7);
    	
       	//Pneumatics control module port
    	public final int pcmPort;
 
    	Pneumatics (int pcmPort){
    		this.pcmPort = pcmPort;
    	} 
    }
    
    /**
     * Enum containing the positions of the ultrasonic sensors in the robot.
     */
    public enum UltrasonicPosition {
    	FRONT, REAR;
    }
}
