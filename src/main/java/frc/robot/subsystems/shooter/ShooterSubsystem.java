// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private final VictorSPX m_shooterLoadingMotor = new VictorSPX(9);

  private final TalonFX m_shooter1 = new TalonFX(40);
  private final TalonFX m_shooter2 = new TalonFX(41);

  public static final int kSlotIdx = 0;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
  public final static Gains kGains_Velocit  = new Gains( .1, 0.001, .5, 1023.0/20660.0,  300,  1.00);
  public double m_rpm;


  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    m_shooterLoadingMotor.setInverted(true);

    m_shooter1.configFactoryDefault();
    m_shooter2.configFactoryDefault();
    m_shooter1.configNeutralDeadband(0.001);
    m_shooter2.configNeutralDeadband(0.001);
    m_shooter1.setNeutralMode(NeutralMode.Coast);
    m_shooter2.setNeutralMode(NeutralMode.Coast);
    m_shooter1.setInverted(true);
    m_shooter2.setInverted(true);
    m_shooter2.follow(m_shooter1);

    m_shooter1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

    m_shooter1.configNominalOutputForward(0, kTimeoutMs);
		m_shooter1.configNominalOutputReverse(0, kTimeoutMs);
		m_shooter1.configPeakOutputForward(1, kTimeoutMs);
		m_shooter1.configPeakOutputReverse(-1, kTimeoutMs);

    m_shooter1.config_kF(kPIDLoopIdx, kGains_Velocit.kF, kTimeoutMs);
		m_shooter1.config_kP(kPIDLoopIdx, kGains_Velocit.kP, kTimeoutMs);
		m_shooter1.config_kI(kPIDLoopIdx, kGains_Velocit.kI, kTimeoutMs);
		m_shooter1.config_kD(kPIDLoopIdx, kGains_Velocit.kD, kTimeoutMs);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public void ShooterLoaderForward(){
    m_shooterLoadingMotor.set(VictorSPXControlMode.PercentOutput,-1);
  }
  public void ShooterLoaderReverse(){
    m_shooterLoadingMotor.set(VictorSPXControlMode.PercentOutput,1);
  }
  public void ShooterLoaderStop(){
    m_shooterLoadingMotor.set(VictorSPXControlMode.PercentOutput,0);
  }

  public void ShooterSetVelocity(double rpm){
    rpm = m_rpm;

    /**
			 * Convert 2000 RPM to units / 100ms.
			 * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
		double targetVelocity_UnitsPer100ms = rpm * 2048.0 / 600.0;
		
    /* 2000 RPM in either direction */
		m_shooter1.set(TalonFXControlMode.Velocity, -targetVelocity_UnitsPer100ms);


    Shuffleboard.getTab("Shooter").add("Shooter Requested RPM", rpm);
    Shuffleboard.getTab("Shooter").add("Shooter Actual RPM", m_shooter1.getSelectedSensorVelocity() * 600 / 2048);
    
  }

  public void ShooterStop(){
    m_shooter1.set(TalonFXControlMode.PercentOutput,0);
    }

  

}
