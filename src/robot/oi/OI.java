package robot.oi;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
import robot.utils.R_Extreme3DPro_GameController;
import robot.utils.R_GameController;
import robot.utils.R_GameController.Axis;
import robot.utils.R_GameController.Button;
import robot.utils.R_GameController.Stick;
import robot.utils.R_GameController.Trigger;
import robot.utils.R_GameControllerFactory;

public class OI {	
	private NetworkTable visionTable;
	
	public OI() {
		visionTable = NetworkTable.getTable("GRIP/TargetInfo");
	}
	
	/** Drive things to fix: 
	 * -Outer_Intake_Boulder: Intake goes too low/out of range. Doesn't stop after triggering sensor 
	 * -Port over the rest of shooter functionality & bind to convenient buttons
	 **/
	public enum ButtonMap  {
		
		//REMOTE 1
		RESET_GYRO(Button.BACK),
		CALIBRATE_GYRO(Button.START),
		SHOOT_BOULDER(Button.B),
		
		// Intake Commands
		OUTER_INTAKE_BOULDER(Button.LEFT_BUMPER),
		EXTAKE_BOULDER(Button.RIGHT_BUMPER),
		WIND_UP_SHOOTER(Button.A),
		ROTATE_ARM_PICKUP_POS(Button.X),
		ROTATE_ARM_UPPER_POS(Button.Y),

		// cancels and unjams
		CANCEL_COMMAND(Button.BACK),
		
		//REMOTE 2
		

		WIND_UP_BANK_SHOT(Button.BUTTON3),
		AUTO_SHOT_ALIGN(Button.BUTTON4),
		MANUAL_SHOT_ALIGN(Button.BUTTON5),
		ARM_PID_OVERRIDE(Button.BUTTON6),
		
		ROTATE_ARM_DRIVE_POS(Button.BUTTON11);
		
		
		private Button button;

		ButtonMap(Button button) {
			this.button = button;
		}

		public Button getButton() {
			return this.button;
		}
	}
	
	public enum TriggerMap {
		
		//Driver Controls
		INNER_INTAKE_BOULDER(Trigger.LEFT), SHIFT_GEARS(Trigger.RIGHT);
		
		private Trigger trigger;

		TriggerMap(Trigger trigger) {
			this.trigger = trigger;
		}

		public Trigger getTrigger() {
			return this.trigger;
		}
	}
	
	public enum POVMap {
		UP(0), RIGHT(90), DOWN(180), LEFT(270);
		
		private int angle;
		
		POVMap(int angle) {
			this.angle = angle;
		}
		
		public int getAngle() {
			return this.angle;
		}
	}
	
	public enum Nudge { LEFT, RIGHT, NONE };
	
	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private R_Extreme3DPro_GameController operatorStick = new R_Extreme3DPro_GameController(1);
	
	
	public boolean getAutoAlignShotButton() {
		return operatorStick.getButton(ButtonMap.AUTO_SHOT_ALIGN.getButton());
	}
	
	public boolean getManualAlignShotButton() {
		return operatorStick.getButton(ButtonMap.MANUAL_SHOT_ALIGN.getButton());
	}
	

	public boolean getOuterIntakeBoulderButton() {
		return driverStick.getButton(ButtonMap.OUTER_INTAKE_BOULDER.getButton());
	}
	
	public double getInnerIntakeBoulderButton() {
		return driverStick.getTrigger(TriggerMap.INNER_INTAKE_BOULDER.getTrigger());
	}
	
	public double getGearShiftButton() {
		return driverStick.getTrigger(TriggerMap.SHIFT_GEARS.getTrigger());
	}
	
	public boolean getExtakeBoulderButton() {
		return driverStick.getButton(ButtonMap.EXTAKE_BOULDER.getButton());
	}
	
	public boolean getResetGyroButton() {
		return driverStick.getButton(ButtonMap.RESET_GYRO.getButton());
	}
	
	public boolean getWindUpBankShotButton() {
		return operatorStick.getButton(ButtonMap.WIND_UP_BANK_SHOT.getButton());
	}
	
	public boolean getWindUpShooterButton() {
		return operatorStick.getButton(ButtonMap.WIND_UP_SHOOTER.getButton());
	}
	
	public boolean getShootButton() {
		return operatorStick.getButton(ButtonMap.SHOOT_BOULDER.getButton());
	}
	
	public boolean getBallStuckButton() {
		return operatorStick.getButton(Button.A);
	}
	
	public boolean getRotateArmLowPosButton() {
		return driverStick.getButton(ButtonMap.ROTATE_ARM_PICKUP_POS.getButton());
	}
	
	public boolean getRotateArmDrivePosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_DRIVE_POS.getButton());
	}
	
	public boolean getRotateArmUpperPosButton() {
		return driverStick.getButton(ButtonMap.ROTATE_ARM_UPPER_POS.getButton());
	}
	
	public double getArmAngle() {
		if (getRotateArmLowPosButton()) {
			return RobotMap.ArmLevel.LOW_LEVEL.angle;
		}
		if (getRotateArmDrivePosButton()) {
			return RobotMap.ArmLevel.DRIVE_LEVEL.angle;
		}
		if(getRotateArmUpperPosButton()) {
			return RobotMap.ArmLevel.UPPER_LIMIT.angle;
		}

		return -1.0;
	}
	
	public double getArmSpeed() {
		return operatorStick.getAxis(Axis.Y) * operatorStick.getAxis(Axis.Y) * operatorStick.getAxis(Axis.Y);
	}

	
	public boolean getArmPIDOverride() {
		return operatorStick.getButton(ButtonMap.ARM_PID_OVERRIDE.getButton());
	}
	
	public double getSpeed() {
		double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
		return Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}
	
	public double getPOV() {
		return driverStick.getPOVAngle();
	}
	
	public boolean getRotateLeft() {
		return (getPOV() == POVMap.LEFT.getAngle());
	}
	
	public boolean getRotateRight() {
		return (getPOV() == POVMap.RIGHT.getAngle());
	}

	public double getShootSpeed() {
		double stickValue = operatorStick.getAxis(Axis.SLIDER);
		return Math.round(stickValue * Math.abs(stickValue) * 100) / 100.0;
	}
	
	public boolean getCancel() {
		return driverStick.getButton(ButtonMap.CANCEL_COMMAND.getButton());
	}
	
	public boolean getGyroReset() {
		return driverStick.getButton(ButtonMap.RESET_GYRO.getButton());
	}

	public boolean getGyroCalibrate() {
		return driverStick.getButton(ButtonMap.CALIBRATE_GYRO.getButton());
	}
	
    public double getVisionTargetCenter() {
    	double [] xValues = visionTable.getNumberArray("centerX", new double [0]);
    	if (xValues.length != 1) {
    		return RobotMap.NO_VISION_TARGET;
    	}
    	return xValues[0];
    }
     
   
    public double getJoystickTargetCenter() {
    	return operatorStick.getAxis(Axis.X);
    }

	/**
	 * Update the periodic running elements of the dashboard
	 * <p>
	 * i.e. all toggle buttons
	 */
	public void periodic() {
		if (Math.abs(operatorStick.getAxis(Axis.X)) < 0.5) {
		}
	}

	/**
	 * Put any items on the dashboard
	 */
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controllers", driverStick.toString());
		SmartDashboard.putString("Operator Controllers", operatorStick.toString());
		SmartDashboard.putNumber("Vision Target Center", getVisionTargetCenter());
		SmartDashboard.putBoolean("Target Lock", (getVisionTargetCenter() != RobotMap.NO_VISION_TARGET));
		SmartDashboard.putNumber("X-Operator", operatorStick.getAxis(Axis.X));
	}
	
}
