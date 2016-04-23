package robot.utils;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class R_Subsystem extends Subsystem {

	public void periodic() {}
	
	public void init() {}
	
	/**
	 * Update the dashboard with driver information.
	 */
	public abstract void updateDashboard();
	
	/**
	 * Updates the dashboard with debug information.
	 */
	public abstract void debugDashboard();
}
