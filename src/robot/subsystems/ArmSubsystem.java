package robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
import robot.commands.arm.JoystickArmCommand;
import robot.utils.R_AbsoluteEncoder;
import robot.utils.R_PIDController;
import robot.utils.R_PIDInput;
import robot.utils.R_SafetyVictor;
import robot.utils.R_Subsystem;
import robot.utils.R_Victor;

public class ArmSubsystem extends R_Subsystem {

	private static double MAX_ARM_ENCODER = 280;

	DigitalInput armMaxHeight = new DigitalInput(RobotMap.SensorMap.ARM_UPPER_LIMIT.port);
	DigitalInput armMinHeight = new DigitalInput(RobotMap.SensorMap.ARM_LOWER_LIMIT.port);
	R_SafetyVictor armDeployMotor = new R_SafetyVictor(RobotMap.MotorMap.ARM_DEPLOY_MOTOR, armMaxHeight, armMinHeight);
	R_Victor armIntakeMotor = new R_Victor(RobotMap.MotorMap.ARM_INTAKE_MOTOR);

	R_AbsoluteEncoder armEncoder = new R_AbsoluteEncoder(2, RobotMap.ANALOG_OFFSET);

	R_PIDInput armPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return armEncoder.getAngle() / MAX_ARM_ENCODER;
		}
	};

	PIDOutput armPIDOutput = new PIDOutput() {

		@Override
		public void pidWrite(double speed) {
			armDeployMotor.set(speed);
		}
	};
	R_PIDController armPID = new R_PIDController(4.0, 0.0, 0.0, 0.0, armPIDInput, armPIDOutput);

	private boolean armDeployed = false;

	public void init() {

	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickArmCommand());
	}

	@Override
	public void periodic() {
		armPID.calculate();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Deploy Motor", armDeployMotor);
		SmartDashboard.putNumber("Arm Encoder Voltage", armEncoder.getVoltage());
		SmartDashboard.putNumber("Arm Encoder Angle", armEncoder.getAngle());
		SmartDashboard.putBoolean("Arm Max Height", armMaxHeight.get());
		SmartDashboard.putBoolean("Arm Min Height", armMinHeight.get());
		SmartDashboard.putData("Arm PID", armPID);
	}

	/**
	 * 
	 * @return Whether the arm is currently deployed
	 */
	public boolean getArmDeploy() {
		return armDeployed;
	}

	public void deployArm() {
		if (armDeployed) {
			armDeployMotor.set(1.0);
		} else {
			armDeployMotor.set(-1.0);
		}
	}

	public boolean getArmLowerLimit() {
		return armMinHeight.get();
	}

	public boolean getArmUpperLimit() {
		return armMaxHeight.get();
	}

	public void startArmIntake() {
		armIntakeMotor.set(1.0);
	}

	public void stopArmIntake() {
		armIntakeMotor.set(0.0);
	}
	
	public void startArmIntakeReverse() {
		armIntakeMotor.set(-1.0);
	}

	/**
	 * 
	 * @param armDeployed
	 *            The arm is considered up when this value is true.
	 */
	public void setArmDeploy(boolean armDeployed) {
		this.armDeployed = armDeployed;
	}

	public void resetArmEncoder() {
		armEncoder.reset();
	}

	public void setArmAngle(double armAngle) {

		// Set the arm to the appropriate angle and hold with a PID.
		armPID.setSetpoint(armAngle / MAX_ARM_ENCODER);
		if (!armPID.isEnabled()) {
			armPID.enable();
		}

	}

	public void disableArmPID() {
		armPID.disable();
	}

	/**
	 * 
	 * @param armDeployed
	 *            The arm is considered up when this value is true.
	 */
	public void setArmSpeed(double armSpeed) {
		armDeployMotor.set(armSpeed);
	}
}
