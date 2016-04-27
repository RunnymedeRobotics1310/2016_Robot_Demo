package robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {

	public enum AutoMode {
		DO_NOTHING, 
		DRIVE_AND_SHOOT;
	}
	
	SendableChooser autoCommandChooser = new SendableChooser();
	SendableChooser laneChooser = new SendableChooser();
	SendableChooser defenceChooser = new SendableChooser();
	SendableChooser distanceChooser = new SendableChooser();
	SendableChooser targetChooser = new SendableChooser();
	SendableChooser goalChooser = new SendableChooser();
	SendableChooser autoModeChooser = new SendableChooser();

	public AutoChooser() {
		autoCommandChooser.addDefault("Drive and Shoot",  "Drive and Shoot");
		autoCommandChooser.addObject("Do nothing", "Do Nothing");

		laneChooser.addDefault("1", 1);
		laneChooser.addObject("2", 2);
		laneChooser.addObject("3", 3);
		laneChooser.addObject("4", 4);
		laneChooser.addObject("5", 5);

		defenceChooser.addDefault("Low Bar",         "Low Bar");
		defenceChooser.addObject("Ramparts",        "Ramparts");
		defenceChooser.addObject("Moat",            "Moat");
		defenceChooser.addObject("Rock Wall",       "Rock Wall");
		defenceChooser.addObject("Rough Terrain",   "Rough Terrain");
		defenceChooser.addObject("Portcullis",      "Portcullis");
		defenceChooser.addObject("Cheval de Frise", "Cheval de Frise");

		distanceChooser.addDefault("Close", "Close");
		distanceChooser.addObject("Far",   "Far");

		targetChooser.addDefault("Left",   "Left");
		targetChooser.addObject("Center", "Center");
		targetChooser.addObject("Right",  "Right");
		
		goalChooser.addDefault("High", "High");
		goalChooser.addObject("Low", "Low");
		
		autoModeChooser.addDefault("Single Boulder", "Single Boulder");
		autoModeChooser.addDefault("Two Boulder", "Two Boulder");

		SmartDashboard.putData("Auto command",     	autoCommandChooser);
		SmartDashboard.putData("Slot position", 	laneChooser);
		SmartDashboard.putData("Defences",      	defenceChooser);
		SmartDashboard.putData("Distance",      	distanceChooser);
		SmartDashboard.putData("Target",        	targetChooser);
		SmartDashboard.putData("Goal",          	goalChooser);
		SmartDashboard.putData("Auto mode",     	autoModeChooser);
	}

	/**
	 * 
	 * @return The selected lane, as an integer
	 */
	public int getSelectedSlot() {
		return (int) laneChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected defense, as a string
	 */
	public String getSelectedDefence() {
		return (String) defenceChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected distance, as a string
	 */
	public String getSelectedDistance() {
		return (String) distanceChooser.getSelected();
	}

	
	/**
	 * 
	 * @return The selected target, as a string
	 */
	public String getSelectedTarget(){
		return (String) targetChooser.getSelected();
	}
	
	/**
	 * 
	 * @return The selected goal, as a String
	 */
	public String getSelectedGoal() {
		return (String) goalChooser.getSelected();
	}
	
	/**
	 * 
	 * @return The selected goal, as a String
	 */
	public String getSelectedAutoCommand() {
		return (String) autoCommandChooser.getSelected();
	}
	
	/**
	 * 
	 * @return The selected auto goal, as a String
	 */
	public String getSelectedAutoMode() {
		return (String) autoModeChooser.getSelected();
	}
}