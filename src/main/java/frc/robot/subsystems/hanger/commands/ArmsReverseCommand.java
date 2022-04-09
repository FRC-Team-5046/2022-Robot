// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanger.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.hanger.HangerSubsystem;

public class ArmsReverseCommand extends CommandBase {

  private final HangerSubsystem m_hangerSubsystem;

  /** Creates a new IntakeArmDown. */
  public ArmsReverseCommand(HangerSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hangerSubsystem = subsystem;
    addRequirements(m_hangerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hangerSubsystem.hangArmReverse();;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
