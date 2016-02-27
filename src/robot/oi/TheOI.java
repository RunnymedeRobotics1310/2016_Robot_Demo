package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Field.Defense;
import robot.Field.Goal;
import robot.Field.Lane;
import robot.Field.Slot;
import robot.commands.auto.AutoDriveAndShootCommand;
import robot.utils.R_Extreme3DPro_GameController;
import robot.utils.R_GameController;
import robot.utils.R_GameControllerFactory;
import robot.utils.R_GameController.Axis;
import robot.utils.R_GameController.Button;
import robot.utils.R_GameController.Stick;
import robot.utils.R_GameController.Trigger;

public class TheOI {

	public enum ButtonMap  {
		//Driver Controls
		OUTER_INTAKE_BOULDER(Button.LEFT_BUMPER), EXTAKE_BOULDER(Button.B), RESET_GYRO(Button.BACK), CANCEL_COMMAND(Button.X),
		
		//Operator Controls
		WARM_UP_SHOOTER(Button.BUTTON2), SHOOT_BOULDER(Button.BUTTON1), ROTATE_ARM_MIN_POS(Button.BUTTON9), ROTATE_ARM_INTAKE_POS(Button.BUTTON10), ROTATE_ARM_DRIVE_POS(Button.BUTTON12), ROTATE_ARM_MAX_POS(Button.BUTTON11), CLIMB(Button.BUTTON7);
		
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
	
	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private R_Extreme3DPro_GameController operatorStick = new R_Extreme3DPro_GameController(1);
	
	private AutoChooser autoChooser = new AutoChooser();

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
	
	public boolean getWarmUpShooterButton() {
		return operatorStick.getButton(ButtonMap.WARM_UP_SHOOTER.getButton());
	}
	
	public boolean getShootButton() {
		return operatorStick.getButton(ButtonMap.SHOOT_BOULDER.getButton());
	}
	
	public boolean getRotateArmMinPosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_MIN_POS.getButton());
	}
	
	public boolean getRotateArmIntakePosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_INTAKE_POS.getButton());
	}
	
	public boolean getRotateArmDrivePosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_DRIVE_POS.getButton());
	}
	
	public boolean getRotateArmMaxPosButton() {
		return operatorStick.getButton(ButtonMap.ROTATE_ARM_MAX_POS.getButton());
	}
	
	public boolean getClimbButton() {
		return operatorStick.getButton(ButtonMap.CLIMB.getButton());
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
	
	public Command getAutoCommand() {

		switch (autoChooser.getAutoMode()) {
		case "Do Nothing":
			return null;
		case "Drive and Shoot":
			return new AutoDriveAndShootCommand(getSlot(), getDefense(), getLane(), getGoal());
		default:
			return null;
		}
	}
	
	public Defense getDefense() {
		return Defense.toEnum(autoChooser.getSelectedDefence());
	}

	public Slot getSlot() {
		return Slot.toEnum(autoChooser.getSelectedSlot());
	}
	
	public Lane getLane() {
		return Lane.toEnum(autoChooser.getSelectedDistance());
	}

	public Goal getGoal() {
		return Goal.toEnum(autoChooser.getSelectedGoal());
	}

	/**
	 * Update the periodic running elements of the dashboard
	 * <p>
	 * i.e. all toggle buttons
	 */
	public void periodic() {
	}

	/**
	 * Put any items on the dashboard
	 */
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controllers", driverStick.toString());
		SmartDashboard.putString("Operator Controllers", operatorStick.toString());
	}
	
}