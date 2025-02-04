package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Positionestimate extends SubsystemBase {
    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;

    private final double limelightMountAngleDegrees = 25.0;
    private final double limelightLensHeightInches = 20.0;
    private final double goalHeightInches = 60.0;

    public Positionestimate() {
        // Get the NetworkTable for Limelight
        table = NetworkTableInstance.getDefault().getTable("limelight");

        // Get the tx, ty, and ta values from the Limelight
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    public double getTx() {
        return tx.getDouble(0.0);
    }

    public double getTy() {
        return ty.getDouble(0.0);
    }

    public double getTa() {
        return ta.getDouble(0.0);
    }

    public double calculateDistanceToGoal() {
        // Calculate the angle to the goal in degrees
        double angleToGoalDegrees = limelightMountAngleDegrees + getTy();

        // Convert angle to radians
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0);

        // Calculate the distance from Limelight to the goal in inches
        return (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    }

    @Override
    public void periodic() {
        // Display the distance and tx, ty, ta values on SmartDashboard

        SmartDashboard.putNumber("Apriltag to robot distance (inches)", calculateDistanceToGoal());
    }
}
