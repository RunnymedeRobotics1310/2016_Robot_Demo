package robot.utils;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the base class for all of the robot's subystems. The only difference
 * between this and {@link Subsystem} is that this class enforces the usage of
 * the init(), peridic(), updateDashboard() and debugDashboard() methods.
 */
public abstract class R_Subsystem extends Subsystem {

	public void periodic() {
	}

	public void init() {
	}

	/**
	 * Update the dashboard with driver information.
	 */
	public abstract void updateDashboard();

	/**
	 * Updates the dashboard with debug information.
	 */
	public abstract void debugDashboard();
}
