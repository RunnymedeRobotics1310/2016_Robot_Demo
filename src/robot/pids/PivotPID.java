package robot.pids;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;
import robot.utils.R_PIDController;
import robot.utils.R_PIDInput;

public class PivotPID {

	/*
	 * Angle PID Controller
	 * 
	 * The angle PID controller is declared as static so that they can be
	 * adjusted in the SmartDashboard
	 */
	private static R_PIDInput pivotPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getAngleDifference(angleSetpoint) / 180.0;
		}
	};

	private static PIDOutput pivotPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputValue = output;
		}
	};

	private static double angleSetpoint = 0.0;
	private static double pidOutputValue = 0.0;

	// TODO Verify this values are correct
	private static R_PIDController pivotPIDController = new R_PIDController(5.0, 0.3, 0.0, 1.0, pivotPIDInput,
			pivotPIDOutput);

	public static void setEnabled(boolean enabled) {
		if (enabled) {
			pivotPIDController.enable();
		} else {
			pivotPIDController.reset();
		}
	}

	public static boolean isEnabled() {
		return pivotPIDController.isEnabled();
	}

	public static double getOutput() {
		return pidOutputValue;
	}

	public static void setSetpoint(double setpoint) {
		angleSetpoint = setpoint;
	}

	public static void periodic() {
		pivotPIDController.calculate();
	}

	public static void updateDashboard() {
		SmartDashboard.putData("PivotPID", pivotPIDController);
	}

}
