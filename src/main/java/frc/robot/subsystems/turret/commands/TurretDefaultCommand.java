// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.TurretSubsystem;

public class TurretDefaultCommand extends CommandBase {

  private final TurretSubsystem m_turretSubsystem;
  private final DoubleSupplier m_speed;

  /** Creates a new TurretDefaultCommand. */
  public TurretDefaultCommand(TurretSubsystem subsystem, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turretSubsystem = subsystem;
    m_speed = speed;
    addRequirements(m_turretSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_turretSubsystem.TurretManual(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
