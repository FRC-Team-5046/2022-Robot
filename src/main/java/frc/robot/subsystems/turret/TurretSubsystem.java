// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */

  private final CANSparkMax m_turret = new CANSparkMax(8,MotorType.kBrushless);

  float FWDSoftLimitValueDefault = 250;
  float REVSoftLimitValueDefault = 0;
  boolean FWDSoftLimitEnabledDefault = true;
  boolean REVSoftLimitEnabledDefault = true;

  private ShuffleboardTab tab = Shuffleboard.getTab("Turret");
  private NetworkTableEntry FWDSoftLimitEnabled =
    tab.add("Forward Soft Limit Enabled",FWDSoftLimitEnabledDefault)
    .getEntry();
  private NetworkTableEntry REVSoftLimitEnabled =
    tab.add("Reverse Soft Limit Enabled",REVSoftLimitEnabledDefault)
    .getEntry();
  private NetworkTableEntry FWDSoftLimitValue =
    tab.add("Forward Soft Limit",FWDSoftLimitValueDefault)
    .getEntry();
  private NetworkTableEntry REVSoftLimitValue =
    tab.add("Reverse Soft Limit",REVSoftLimitValueDefault)
    .getEntry();

  
  public TurretSubsystem() {
    m_turret.restoreFactoryDefaults();
    m_turret.setIdleMode(IdleMode.kBrake);
    m_turret.setInverted(false);

    m_turret.setSoftLimit(SoftLimitDirection.kForward, FWDSoftLimitValueDefault);
    m_turret.setSoftLimit(SoftLimitDirection.kReverse, REVSoftLimitValueDefault);
   
    m_turret.enableSoftLimit(SoftLimitDirection.kForward, FWDSoftLimitEnabledDefault);
    m_turret.enableSoftLimit(SoftLimitDirection.kReverse, FWDSoftLimitEnabledDefault);

    
      // tab.add("Forward Soft Limit Enabled",m_turret.isSoftLimitEnabled(SoftLimitDirection.kForward));
      // tab.add("Reverse Soft Limit Enabled",m_turret.isSoftLimitEnabled(SoftLimitDirection.kReverse));
      // tab.add("Forward Soft Limit",m_turret.getSoftLimit(SoftLimitDirection.kForward));                        
      // tab.add("Reverse Soft Limit",m_turret.getSoftLimit(SoftLimitDirection.kReverse));
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    m_turret.enableSoftLimit(SoftLimitDirection.kForward, FWDSoftLimitEnabled.getBoolean(FWDSoftLimitEnabledDefault));
    m_turret.enableSoftLimit(SoftLimitDirection.kReverse, REVSoftLimitEnabled.getBoolean(FWDSoftLimitEnabledDefault));
    m_turret.setSoftLimit(SoftLimitDirection.kForward, (float) FWDSoftLimitValue.getDouble(REVSoftLimitValueDefault));
    m_turret.setSoftLimit(SoftLimitDirection.kReverse, (float) REVSoftLimitValue.getDouble(FWDSoftLimitValueDefault));
  }

  public void TurretManual(double speed) {
    m_turret.set(speed);
  }
  

}
