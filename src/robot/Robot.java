package robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import robot.oi.OI;
import robot.pids.GoStraightPID;
import robot.pids.RotateToAnglePID;
import robot.subsystems.ArmSubsystem;
import robot.subsystems.ChassisSubsystem;
import robot.subsystems.ClimberSubsystem;
import robot.subsystems.ShooterSubsystem;
import robot.utils.R_Subsystem;

/**
*   _________  _______   ________  _____ ______            _____  ________    _____  ________     
*  |\___   ___\\  ___ \ |\   __  \|\   _ \  _   \         / __  \|\_____  \  / __  \|\   __  \    
*  \|___ \  \_\ \   __/|\ \  \|\  \ \  \\\__\ \  \       |\/_|\  \|____|\ /_|\/_|\  \ \  \|\  \   
*       \ \  \ \ \  \_|/_\ \   __  \ \  \\|__| \  \      \|/ \ \  \    \|\  \|/ \ \  \ \  \\\  \  
*        \ \  \ \ \  \_|\ \ \  \ \  \ \  \    \ \  \          \ \  \  __\_\  \   \ \  \ \  \\\  \ 
*         \ \__\ \ \_______\ \__\ \__\ \__\    \ \__\          \ \__\|\_______\   \ \__\ \_______\
*          \|__|  \|_______|\|__|\|__|\|__|     \|__|           \|__|\|_______|    \|__|\|_______|                                                                      
*/

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static boolean debugMode = false;

	// Declare all subsystems and add them to the list of subsystems
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final ArmSubsystem armSubsystem = new ArmSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

	public static OI oi;

	public static ArrayList<R_Subsystem> subsystemList = new ArrayList<>();

	Command autonomousCommand;

	public void autonomousInit() {

		autonomousCommand = oi.getAutoCommand();

		chassisSubsystem.resetGyroHeading();

		// schedule the autonomous command
		Scheduler.getInstance().add(autonomousCommand);

		updateDashboard();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	public void disabledInit() {
		updateDashboard();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

	public void robotInit() {

		oi = new OI();

		// Add all the subsystems to the subsystem list.
		subsystemList.add(chassisSubsystem);
		subsystemList.add(shooterSubsystem);
		subsystemList.add(armSubsystem);

		for (R_Subsystem s : subsystemList) {
			s.init();
		}

		updateDashboard();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		updateDashboard();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	private void subsystemPeriodic() {
		// Update all subsystem runtime data.
		for (R_Subsystem r : subsystemList) {
			r.periodic();
		}
		oi.periodic();

		// Command PID updates
		GoStraightPID.periodic();
		RotateToAnglePID.periodic();
	}

	private void updateDashboard() {
		// Update all subsystems and the OI dashboard items.
		for (R_Subsystem r : subsystemList) {
			r.updateDashboard();
			if (debugMode) {
				r.debugDashboard();
			}
		}
		oi.updateDashboard();

		GoStraightPID.updateDashboard();
		RotateToAnglePID.updateDashboard();
		// Put the currently scheduled commands on the dashboard
		// SmartDashboard.putData("SchedulerCommands", Scheduler.getInstance());
	}
}
