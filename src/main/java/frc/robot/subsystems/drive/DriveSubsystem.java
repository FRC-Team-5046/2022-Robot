// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_leftMotor1 = new CANSparkMax(1,MotorType.kBrushless);
  private final CANSparkMax m_leftMotor2 = new CANSparkMax(3,MotorType.kBrushless);
  private final CANSparkMax m_leftMotor3 = new CANSparkMax(5,MotorType.kBrushless);
  
  private final CANSparkMax m_rightMotor1 = new CANSparkMax(2,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor2 = new CANSparkMax(4,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor3 = new CANSparkMax(6,MotorType.kBrushless);

  private final DoubleSolenoid m_shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,6,1);


  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2, m_leftMotor3);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightMotor1,m_rightMotor2, m_rightMotor3);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors,m_rightMotors);


  public String shifterState;
  
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {


    m_leftMotor1.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();
    m_leftMotor3.restoreFactoryDefaults();
    m_rightMotor1.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();
    m_rightMotor3.restoreFactoryDefaults();

    m_leftMotor1.setIdleMode(IdleMode.kCoast);
    m_leftMotor2.setIdleMode(IdleMode.kCoast);
    m_leftMotor3.setIdleMode(IdleMode.kCoast);

    m_rightMotor1.setIdleMode(IdleMode.kCoast);
    m_rightMotor2.setIdleMode(IdleMode.kCoast);
    m_rightMotor3.setIdleMode(IdleMode.kCoast);

    m_leftMotor1.setInverted(true);
    m_leftMotor2.setInverted(true);
    m_leftMotor3.setInverted(true);
    

  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void shiftHigh(){
    m_shifter.set(Value.kForward);
    shifterState = "High";
  }

  public void shiftLow(){
    m_shifter.set(Value.kReverse);
    shifterState = "Low";
  }

  public void arcadeDrive(double fwd, double rot){

    if (shifterState == "High"){
      m_drive.arcadeDrive(fwd * .75  , rot *.75 );
    } else if (shifterState == "Low") {
      m_drive.arcadeDrive(fwd , rot);
    }
    
  }

}
