package robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.R_Subsystem;
import robot.R_Victor;
import robot.RobotMap;
import robot.commands.shoot.JoystickShootCommand;

public class ShooterSubsystem extends R_Subsystem {

	public enum IntakeReverseSpeed {LOW, HIGH}

	Victor intakeMotor  = new R_Victor(RobotMap.MotorMap.INTAKE_MOTOR);
	Victor shooterMotor = new R_Victor(RobotMap.MotorMap.SHOOTER_MOTOR);
	
	DigitalInput boulderProximitySensor = new DigitalInput(RobotMap.SensorMap.BOULDER_PROXIMITY_SENSOR.port);

	Counter shooterSpeedEncoder  = new Counter(RobotMap.SensorMap.SHOOTER_SPEED_ENCODER.port);
	
	Encoder intakeEncoder        = 
			new Encoder(RobotMap.EncoderMap.INTAKE_ENCODER.ch1,
			            RobotMap.EncoderMap.INTAKE_ENCODER.ch2);
	
	DoubleSolenoid shooterRail = 
			new DoubleSolenoid(RobotMap.Pneumatics.SHOOTER_RAIL_UP.pcmPort, 
					           RobotMap.Pneumatics.SHOOTER_RAIL_DOWN.pcmPort);
	
	
	R_PIDInput intakeLockPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -(RobotMap.EncoderMap.INTAKE_ENCODER.inverted ? -1.0 : 1.0) * intakeEncoder.getDistance();
		}
	};

	R_PIDController intakeLockPID = new R_PIDController(0.02, 0.0, 0.0, 0.0, intakeLockPIDInput, intakeMotor);

	// Initialize the subsystem to Disable the intake PID.
	public ShooterSubsystem() {
		intakeLockPID.setSetpoint(0.0);
		intakeLockPID.disable();
	}

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
		intakeEncoder.reset();
		intakeLockPID.reset();
		intakeMotor.set(0.0);
		intakeLockPID.enable();
	}
	
	@Override
	public void periodic() {
		intakeLockPID.calculate();
	}
	
	public void resetIntakeEncoder() {
		intakeEncoder.reset();
	}
	
	public void setBoulderRetracted(boolean ballRetracted) {
		this.boulderRetracted = ballRetracted;
	}
	
	public void setIntakeMotorReverse(IntakeReverseSpeed intakeReverseSpeed) {

		if (intakeLockPID.isEnabled()) {
			intakeLockPID.disable();
		}
		
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

		if (intakeLockPID.isEnabled()) {
			intakeLockPID.disable();
		}
		
		intakeMotor.set(1.0); // Speed of intake when taking in a boulder	
	}
	
	public void startShooterMotor() {
		shooterMotor.set(1.0);
	}
	
	public void startShooterMotorReverse(){
		shooterMotor.set(-0.5);
	}
	
	public void stopIntakeMotor() {

		if (intakeLockPID.isEnabled()) {
			intakeLockPID.disable();
		}
		
		intakeMotor.set(0.0);
	}
	
	public void stopShooterMotor(){
		shooterMotor.set(0.0);
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Intake Lock PID" , intakeLockPID);
		SmartDashboard.putNumber("Intake Lock PID Output", intakeMotor.get());
		SmartDashboard.putData("Intake motor", intakeMotor);
		SmartDashboard.putNumber("Shooter Speed" , getShooterSpeed());
		SmartDashboard.putNumber("Intake Speed" , getIntakeSpeed());
		SmartDashboard.putNumber("Intake Distance" , getIntakeDistance());
		SmartDashboard.putBoolean("Boulder Loaded" , isBoulderLoaded());
	}
}
