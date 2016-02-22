package robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import robot.R_Subsystem;

public class CameraSubsystem extends R_Subsystem {

    CameraServer server;

	public void init() {
	    server = CameraServer.getInstance();
	    server.setQuality(20);
	    server.startAutomaticCapture("cam0");
	    
	}

	public void initDefaultCommand() {
	}

	@Override
	public void periodic() {

	}

	@Override
	public void updateDashboard() {

	}

}
