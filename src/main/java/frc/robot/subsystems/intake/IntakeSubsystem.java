// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class IntakeSubsystem extends SubsystemBase {

  private final VictorSPX m_intakeStageOneMotor = new VictorSPX(7);
  private final VictorSPX m_intakeStageTwoMotor = new VictorSPX(11);
  private final DoubleSolenoid m_intakearm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    m_intakeStageOneMotor.setInverted(true);
    m_intakeStageTwoMotor.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeStageOneForward(){
    m_intakeStageOneMotor.set(VictorSPXControlMode.PercentOutput, -1);
  }
  public void intakeStageOneReverse(){
    m_intakeStageOneMotor.set(VictorSPXControlMode.PercentOutput, 1);
  }
  public void intakeStageOneStop(){
    m_intakeStageOneMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }
  
  public void intakeStageTwoForward(){
    m_intakeStageTwoMotor.set(VictorSPXControlMode.PercentOutput, -1);
  }
  public void intakeStageTwoReverse(){
    m_intakeStageTwoMotor.set(VictorSPXControlMode.PercentOutput, 1);
  }
  public void intakeStageTwoStop(){
    m_intakeStageTwoMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  public void intakeArmUp(){
    m_intakearm.set(Value.kForward);
  }

  public void intakeArmDown(){
    m_intakearm.set(Value.kReverse);
  }

  public void intakeArmToggle(){
    if (m_intakearm.get() == Value.kForward){
      m_intakearm.set(Value.kReverse);
    } else if (m_intakearm.get() == Value.kReverse){
      m_intakearm.set(Value.kForward);
    }
  }


}
