package robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class JoystickArmCommand extends Command {

	boolean armPIDEnabled = false;
	
    public JoystickArmCommand() {
       requires(Robot.armSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*if (Robot.oi.getArmEncoderReset()) {
    		Robot.armSubsystem.resetArmEncoder();
    	}*/
    	
    	/*if (Robot.oi.getArmPIDToggle()) {
    		armPIDEnabled = false;
    		Robot.armSubsystem.disableArmPID();
    	}*/
    	
    	if (!armPIDEnabled) {
        	Robot.armSubsystem.setArmSpeed(Robot.oi.getArmSpeed());
    	}

    	if (Robot.oi.getArmAngle() >= 0.0) {
    		Robot.armSubsystem.setArmAngle(Robot.oi.getArmAngle());
    		armPIDEnabled = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
