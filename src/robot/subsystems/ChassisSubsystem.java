package robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_Gyro;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.R_Subsystem;
import robot.R_Talon;
import robot.R_Ultrasonic;
import robot.RobotMap;
import robot.commands.drive.JoystickDriveCommand;

public class ChassisSubsystem extends R_Subsystem {

	Talon leftMotor  = new R_Talon(RobotMap.MotorMap.LEFT_MOTOR);
	Talon rightMotor = new R_Talon(RobotMap.MotorMap.RIGHT_MOTOR);
	
	DigitalInput leftProximitySensor   = new DigitalInput(RobotMap.SensorMap.LEFT_PROXIMITY_SENSOR.port);
	DigitalInput centerProximitySensor = new DigitalInput(RobotMap.SensorMap.UPPER_PROXIMITY_SENSOR.port);
	DigitalInput rightProximitySensor  = new DigitalInput(RobotMap.SensorMap.RIGHT_PROXIMITY_SENSOR.port);
	
	Encoder leftEncoder  = new Encoder(RobotMap.EncoderMap.LEFT.ch1, RobotMap.EncoderMap.LEFT.ch2);
	Encoder rightEncoder = new Encoder(RobotMap.EncoderMap.RIGHT.ch1, RobotMap.EncoderMap.RIGHT.ch2);
	
	R_Ultrasonic ultrasonicSensor = new R_Ultrasonic(RobotMap.SensorMap.ULTRASONIC.port);

	DoubleSolenoid ballShifter = 
			new DoubleSolenoid(RobotMap.Pneumatics.BALLSHIFTER_HIGH.pcmPort, 
					           RobotMap.Pneumatics.BALLSHIFTER_LOW .pcmPort);
	
	public  enum Gear { LOW, HIGH };
	private Gear gear = Gear.LOW;
	
	/*
	 * Motor PID Controllers
	 */
	R_PIDInput leftPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return (RobotMap.EncoderMap.LEFT.inverted ? -1.0 : 1.0) *
					leftEncoder.getRate() / RobotMap.EncoderMap.LEFT.maxRate;
		}
	};

	R_PIDInput rightPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return (RobotMap.EncoderMap.RIGHT.inverted ? -1.0 : 1.0) *
					rightEncoder.getRate() / RobotMap.EncoderMap.RIGHT.maxRate;
		}
	};

	R_PIDController leftMotorPID = new R_PIDController(1.0, 0.0, 0.0, 1.0, leftPIDInput, leftMotor);

	R_PIDController rightMotorPID = new R_PIDController(1.5, 0.0, 0.0, 1.0, rightPIDInput, rightMotor);

	ArrayList<R_PIDController> pidControllers = new ArrayList<>();

	// Gyro
	R_Gyro gyro = new R_Gyro(RobotMap.SensorMap.GYRO.port);

	public void init() {
		
		pidControllers.add(leftMotorPID);
		pidControllers.add(rightMotorPID);

		gyro.initGyro();
		gyro.setSensitivity(0.00165 * (360.0 / 365.0));
		gyro.calibrate();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		
		leftMotorPID .setSetpoint(leftSpeed);
		rightMotorPID.setSetpoint(rightSpeed);

		if (!leftMotorPID.isEnabled()) {
			leftMotorPID.enable();
		}
		if (!rightMotorPID.isEnabled()) {
			rightMotorPID.enable();
		}
	}

	public double getCurrentAngle() {
		return gyro.getAngle();
	}

	public double getAngleDifference(double targetAngle) {
		return gyro.getAngleDifference(targetAngle);
	}

	public boolean getProximity() {
		boolean proximity = !leftProximitySensor.get() || !centerProximitySensor.get() || !rightProximitySensor.get();
		SmartDashboard.putBoolean("Proximity Sensor(s) active", proximity);
		return proximity;
	}

	public double getUltrasonicDistance() {
		return this.ultrasonicSensor.getDistance();
	}

	@Override
	public void periodic() {
		// Update all of the PIDs every loop
		for (R_PIDController pid : pidControllers) {
			pid.calculate();
		}
		
		periodicSetShifter();
	}

	private void periodicSetShifter() {

		// If the user is not pushing the turbo, then set to 
		// low gear - reverse mode.
		if (gear == Gear.LOW) {
			ballShifter.set(Value.kReverse);
			return;
		}
		
		// Otherwise the user has selected high gear.
		
		// Determine if the user has selected high gear and then 
		// shift appropriately.
		// The gear cannot shift to high unless the robot has enough
		// speed in the selected direction.
		
		double leftSpeed  = getLeftEncoderSpeed();
		double rightSpeed = getRightEncoderSpeed();
		
		// If the robot is turning then put the shifter in low gear
		if (   leftSpeed < 0 && rightSpeed > 0
			|| leftSpeed > 0 && rightSpeed < 0) {
			ballShifter.set(Value.kReverse);
			return;
		}
		
		// Check that the set speeds are in the same direction
		double leftSetpoint  = leftMotorPID.getSetpoint();
		double rightSetpoint = rightMotorPID.getSetpoint();
		
		// If the robot trying to turn then put the shifter in low gear
		if (   leftSetpoint < 0 && rightSetpoint > 0
			|| leftSetpoint > 0 && rightSetpoint < 0) {
			ballShifter.set(Value.kReverse);
			return;
		}
		
		// Check that the setpoint is in the same direction as the motor.
		// Since we know that the left and right are synched, then we
		// only need to check the left side is consistent (ie. that we
		// are not being pushed backwards by another robot).
		if (   leftSetpoint < 0 && leftSpeed > 0
			|| leftSetpoint > 0 && leftSpeed < 0) {
			ballShifter.set(Value.kReverse);
			return;
		}
		
		// Check that the speed is greater than the limit for the 
		// low gear.
		if (Math.abs(getEncoderSpeed()) > 25.0) {
			ballShifter.set(Value.kForward);
			System.out.println("High Gear");
		}

	}
	
	/**
	 * Gets the approximate distance using encoder counts by averaging the two
	 * encoder distances.
	 * 
	 * @return the approximate distance.
	 */
	public double getEncoderDistance() {

		double leftDistance = this.leftEncoder.getDistance() * 
				(RobotMap.EncoderMap.LEFT.inverted ? - 1.0 : 1.0);
		
		double rightDistance = this.rightEncoder.getDistance() * 
				(RobotMap.EncoderMap.RIGHT.inverted ? - 1.0 : 1.0);
		
		return (leftDistance + rightDistance) / 2.0
				/ RobotMap.EncoderMap.LEFT.countsPerInch;
	}

	/**
	 * Gets the approximate speed using encoder speed by averaging the two
	 * encoder speeds.
	 * 
	 * @return the approximate distance.
	 */
	public double getEncoderSpeed() {
		return (getLeftEncoderSpeed() + getRightEncoderSpeed()) / 2.0
				/ RobotMap.EncoderMap.LEFT.countsPerInch;
	}
	
	private double getLeftEncoderSpeed() {
		return this.leftEncoder.getRate() * 
				(RobotMap.EncoderMap.LEFT.inverted ? - 1.0 : 1.0);
	}

	private double getRightEncoderSpeed() {
		return this.rightEncoder.getRate() * 
				(RobotMap.EncoderMap.RIGHT.inverted ? - 1.0 : 1.0);
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}
	
	/**
	 * Resets the encoder distance.
	 */
	public void resetEncoders() {
		this.leftEncoder.reset();
		this.rightEncoder.reset();
	}

	public void resetUltrasonicSensorFilter() {
		ultrasonicSensor.reset();
	}

	public void resetGyroHeading() {
		gyro.reset();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Left Limit Switch", leftProximitySensor);
		SmartDashboard.putData("Center Limit Switch", centerProximitySensor);
		SmartDashboard.putData("Right Limit Switch", rightProximitySensor);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
		SmartDashboard.putData("Left Motor PID", leftMotorPID);
		SmartDashboard.putData("Right Motor PID", rightMotorPID);
		SmartDashboard.putData("Gyro", gyro);
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		SmartDashboard.putNumber("Ultrasonic Sensor Distance", ultrasonicSensor.getDistance());
		SmartDashboard.putNumber("Raw ultrasonic sensor voltage", ultrasonicSensor.getVoltage());
		SmartDashboard.putString("Transmission", gear.name());
		SmartDashboard.putNumber("Encoder Speed", getEncoderSpeed());
		SmartDashboard.putNumber("Left Encoder Speed", getLeftEncoderSpeed());
		SmartDashboard.putNumber("Right Encoder Speed", getRightEncoderSpeed());
		SmartDashboard.putData("Ball shifter", ballShifter);
	}
}
