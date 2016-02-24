package robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_AbsoluteEncoder;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.R_Subsystem;
import robot.R_Victor;
import robot.RobotMap;
import robot.commands.arm.JoystickArmCommand;

public class ArmSubsystem extends R_Subsystem {
	
	private static double MAX_ARM_ENCODER = 300;

	Victor armDeployMotor = new R_Victor(RobotMap.MotorMap.ARM_DEPLOY_MOTOR);
	Victor armIntakeMotor = new R_Victor(RobotMap.MotorMap.ARM_INTAKE_MOTOR);
	DigitalInput armMaxHeight = new DigitalInput(RobotMap.SensorMap.ARM_UPPER_LIMIT.port);
	DigitalInput armMinHeight = new DigitalInput(RobotMap.SensorMap.ARM_LOWER_LIMIT.port);
	
	R_AbsoluteEncoder armEncoder = new R_AbsoluteEncoder(2);

	R_PIDInput armPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() { return armEncoder.getAngle(); }
	};

	PIDOutput armPIDOutput = new PIDOutput() {

		@Override
		public void pidWrite(double output) {
			armDeployMotor.set(-output);
		}
	};

	R_PIDController armPID = new R_PIDController(1.0, 0.0, 0.0, 0.0, armPIDInput, armDeployMotor);

	ArrayList<R_PIDController> pidControllers = new ArrayList<>();

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

	public void startArmIntake(){
		armIntakeMotor.set(1.0);
	}
	
	public void stopArmIntake(){
		armIntakeMotor.set(0.0);
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
		
		// If the arm angle is not between 0 and 300, then ignore the input
		if (armAngle < 0.0 || armAngle > 300.0) { return; }
		
		// Set the arm to the appropriate angle and hold with a PID.
		armPID.setSetpoint(armAngle);
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
