package robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_Subsystem;
import robot.R_Talon;
import robot.RobotMap;
import robot.commands.shoot.JoystickShootCommand;

public class ShooterSubsystem extends R_Subsystem {

	public enum IntakeReverseSpeed {LOW, HIGH}

	Talon intakeMotor  = new R_Talon(RobotMap.MotorMap.INTAKE_MOTOR);
	Talon shooterMotor = new R_Talon(RobotMap.MotorMap.SHOOTER_MOTOR);
	
	DigitalInput boulderProximitySensor = new DigitalInput(RobotMap.SensorMap.BOULDER_PROXIMITY_SENSOR.port);

	Counter shooterSpeedEncoder  = new Counter(RobotMap.SensorMap.SHOOTER_SPEED_ENCODER.port);
	
	Encoder intakeEncoder        = 
			new Encoder(RobotMap.EncoderMap.INTAKE_ENCODER.ch1,
			            RobotMap.EncoderMap.INTAKE_ENCODER.ch2);
	
	DoubleSolenoid shooterRail = 
			new DoubleSolenoid(RobotMap.Pneumatics.SHOOTER_RAIL_UP.pcmPort, 
					           RobotMap.Pneumatics.SHOOTER_RAIL_DOWN.pcmPort);
	
	private boolean boulderRetracted = false;
	
	public double getIntakeDistance() {
		return intakeEncoder.getDistance();
	}

	public double getIntakeSpeed() {
		return intakeEncoder.getRate();
	}

	public Value getRailPosition() {
		return shooterRail.get();
	}
	
	public double getShooterSpeed() {
		return shooterSpeedEncoder.getRate();
	}
	
	public void init() {
		// Initialize the shooter speed controller to count
		// rpms.  One count = 1 rpm.
		shooterSpeedEncoder.setDistancePerPulse(1.0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickShootCommand());
	}

	public boolean isBoulderLoaded() {
		return !boulderProximitySensor.get();
	}
	
	public boolean isBoulderRetracted() {
		return boulderRetracted;
	}
	
	public void lockIntakeMotor() {
		intakeMotor.set(0.0);
	}
	
	@Override
	public void periodic() {
	}
	
	public void resetIntakeEncoder() {
		intakeEncoder.reset();
	}
	
	public void setBoulderRetracted(boolean ballRetracted) {
		this.boulderRetracted = ballRetracted;
	}
	
	public void setIntakeMotorReverse(IntakeReverseSpeed intakeReverseSpeed) {
		if (intakeReverseSpeed == IntakeReverseSpeed.LOW) {
			intakeMotor.set(-0.3);
		} else {
			intakeMotor.set(-1.0);
		}
	}
	
	public void setRailPosition(Value v) {
		shooterRail.set(v);
	}

	public void startIntakeMotor() {
		intakeMotor.set(1.0); // Speed of intake when taking in a boulder	
	}
	
	public void startShooterMotor() {
		shooterMotor.set(1.0);
	}
	
	public void startShooterMotorReverse(){
		shooterMotor.set(-0.5);
	}
	
	public void stopIntakeMotor() {
		intakeMotor.set(0.0);
	}
	
	public void stopShooterMotor(){
		shooterMotor.set(0.0);
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putNumber("Shooter Speed" , getShooterSpeed());
		SmartDashboard.putNumber("Intake Speed" , getIntakeSpeed());
		SmartDashboard.putNumber("Intake Distance" , getIntakeDistance());
		SmartDashboard.putBoolean("Boulder Loaded" , isBoulderLoaded());
	}
}
