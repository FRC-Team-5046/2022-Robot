// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanger;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HangerSubsystem extends SubsystemBase {
  /** Creates a new HangerSubsystem. */
  private final CANSparkMax m_hangMotor = new CANSparkMax(10,MotorType.kBrushless);
  private final DoubleSolenoid m_brake = new DoubleSolenoid(1,PneumaticsModuleType.CTREPCM, 1, 6);
  private final DoubleSolenoid m_hangarm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,5,2);
  private final DoubleSolenoid m_hangears = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,7,0);




  private float hangerSoftLimitFWD;
  private float hangerSoftLimitREV;

  private NetworkTableEntry hangerSoftLimitFWDEntry =
  Shuffleboard.getTab("Hanger")
    .addPersistent("Hanger Soft Limit Forward", 400)
    .getEntry(); 
    
  private NetworkTableEntry hangerSoftLimitREVEntry =
  Shuffleboard.getTab("Hanger")
    .addPersistent("Hanger Soft Limit Reverse", 0)
    .getEntry(); 
  
  public HangerSubsystem() {

    m_hangMotor.restoreFactoryDefaults();
    m_hangMotor.setIdleMode(IdleMode.kBrake);
    m_hangMotor.setInverted(true);
    
    m_hangMotor.setSoftLimit(SoftLimitDirection.kForward, hangerSoftLimitFWD);
    m_hangMotor.setSoftLimit(SoftLimitDirection.kReverse, hangerSoftLimitREV);

    
    // m_hangMotor.enableSoftLimit(SoftLimitDirection.kReverse, false);
    // m_hangMotor.enableSoftLimit(SoftLimitDirection.kForward, false);
    


  }

  @Override
  public void periodic() {
     hangerSoftLimitFWD = (float) hangerSoftLimitFWDEntry.getDouble(0);
     hangerSoftLimitREV = (float) hangerSoftLimitREVEntry.getDouble(0);

     Shuffleboard.getTab("Hanger").add("Hang Motor Speed", m_hangMotor.get());
     Shuffleboard.getTab("Hanger").add("Brake", m_brake.get());
     Shuffleboard.getTab("Hanger").add("Hang Arm Position", m_hangarm.get());
     Shuffleboard.getTab("Hanger").add("Hang Ear Position", m_hangears.get());

      // This method will be called once per scheduler run
  }


  public void hangArmForward(){
    m_hangarm.set(Value.kForward);
  }

  public void hangArmReverse(){
    m_hangarm.set(Value.kReverse);
  }

  public void hangArmToggle(){
    if (m_hangarm.get() == Value.kForward){
      m_hangarm.set(Value.kReverse);
    } else if (m_hangarm.get() == Value.kReverse){
      m_hangarm.set(Value.kForward);
    }
  }

  public void hangEarsDown(){
    m_hangears.set(Value.kForward);
  }

  public void hangEarsUp(){
    m_hangears.set(Value.kReverse);
  }

  public void hangEarsToggle(){
    if (m_hangears.get() == Value.kForward){
      m_hangears.set(Value.kReverse);
    } else if (m_hangears.get() == Value.kReverse){
      m_hangears.set(Value.kForward);
    }
  }

  public void HangMoterManual(double leftTrigger, double rightTrigger){
    m_hangMotor.set(-leftTrigger + rightTrigger);

    if (leftTrigger + rightTrigger > 0.1 || leftTrigger + rightTrigger < -0.1){
      m_brake.set(Value.kForward);
    } else {
      m_brake.set(Value.kReverse);
    }
  }
}