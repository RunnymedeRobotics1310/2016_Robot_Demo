package robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_AbsoluteEncoder;
import robot.R_Subsystem;
import robot.R_Victor;
import robot.RobotMap;
import robot.commands.arm.JoystickArmCommand;

public class ArmSubsystem extends R_Subsystem {

	Victor armDeployMotor = new R_Victor(RobotMap.MotorMap.ARM_DEPLOY_MOTOR);
	Victor armIntakeMotor = new R_Victor(RobotMap.MotorMap.ARM_INTAKE_MOTOR);
	DigitalInput armMaxHeight = new DigitalInput(RobotMap.SensorMap.ARM_UPPER_LIMIT.port);
	DigitalInput armMinHeight = new DigitalInput(RobotMap.SensorMap.ARM_LOWER_LIMIT.port);
	
	R_AbsoluteEncoder armEncoder = new R_AbsoluteEncoder(2);

	private boolean armDeployed = false;

	public void init() {

	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickArmCommand());
	}

	@Override
	public void periodic() {

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
	
	/**
	 * 
	 * @param armDeployed
	 *            The arm is considered up when this value is true.
	 */
	public void setArmSpeed(double armSpeed) {
		
		armDeployMotor.set(armSpeed);
	}
}
