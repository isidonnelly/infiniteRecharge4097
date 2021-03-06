// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4097.DeepSpace.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4097.DeepSpace.Robot;

/**
 *
 */
public class OperatorDrive extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public OperatorDrive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double sliderValue = -1*Robot.oi.drivestick.getRawAxis(3);
        //below speed multipliers may need to be changed
        //SLIDERMIN is the speed of the robot when the slider is all the way back
        //SLIDERMAX is the speed of the robot when the slider is all
        //the way forwards
        double SLIDERMIN = 0.3;
        double SLIDERMAX = 0.75;
        double speedMultiplier = (SLIDERMAX-SLIDERMIN)/2;
        double speedConstant = SLIDERMAX-speedMultiplier;
        double ZMIN = 0.4;
        double ZMAX = 0.6;
        double zMultiplier = (ZMAX-ZMIN)/2;
        double zConstant = ZMAX-zMultiplier;
        double xValue = Robot.oi.drivestick.getRawAxis(0)*(speedConstant+speedMultiplier*sliderValue)*1.25;
        double yValue = 1*Robot.oi.drivestick.getRawAxis(1)*(speedConstant+speedMultiplier*sliderValue);
        double zValue = Robot.oi.drivestick.getRawAxis(2)*(zConstant+zMultiplier*sliderValue);
        //Cardinal direction stuff to keep
        if (Robot.oi.drivestick.getPOV(0) != -1){
            //Robot.driveTrain.goToTargetAngle(Robot.oi.drivestick.getPOV(0));
        }


        else if ( Robot.oi.drivestick.getRawAxis(2) == -1 ||  Robot.oi.drivestick.getRawAxis(2) == 1){
            Robot.driveTrain.arcadeDrive(yValue, zValue);
        }
        else{
            if (yValue > 0){
                Robot.driveTrain.curvatureDrive(yValue, -xValue);
            }
            else{
                Robot.driveTrain.curvatureDrive(yValue, xValue);
            }
            
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
