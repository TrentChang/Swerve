package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.event.NetworkBooleanEvent;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.LimelightConstants;

public class Limelight extends SubsystemBase{

    NetworkTable table = NetworkTableInstance.getDefault().getTable(LimelightConstants.Name); 

    boolean Detected;
    double ID = 0;
    double tA = 0;
    double tX = 0;

    public double getFiducialID(){
        ID = LimelightHelpers.getFiducialID("");
        return ID;
    }

    public double getTA(){
        tA = LimelightHelpers.getTA(""); // Target area (0% to 100% of image)
        return tA;
    }

    public double getTX(){
        tX = LimelightHelpers.getTX("");  // Horizontal offset from crosshair to target in degrees
        return tX;
    }
    
    @Override
    public void periodic(){
        getFiducialID();
        getTA();
        getTX();

        SmartDashboard.putNumber("Tag_ID", ID);
        SmartDashboard.putNumber("Tag_Area", tA);
        SmartDashboard.putNumber("Tag_X", tX);
    }
}