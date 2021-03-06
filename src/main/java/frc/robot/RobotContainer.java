// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.drive.DriveSubsystem;
import frc.robot.subsystems.drive.commands.DriveDefaultCommand;
import frc.robot.subsystems.drive.commands.DriveShiftHighCommand;
import frc.robot.subsystems.drive.commands.DriveShiftLowCommand;
import frc.robot.subsystems.hanger.HangerSubsystem;
import frc.robot.subsystems.hanger.commands.ArmsToggleCommand;
import frc.robot.subsystems.hanger.commands.EarsToggleCommand;
import frc.robot.subsystems.hanger.commands.HangerDefaultCommand;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.intake.commands.IntakeBallFromGround;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.shooter.commands.ShooterLoaderForwardCommand;
import frc.robot.subsystems.shooter.commands.ShooterSetRPMCommand;
import frc.robot.subsystems.shooter.commands.ShooterStopCommand;
import frc.robot.subsystems.turret.TurretSubsystem;
import frc.robot.subsystems.turret.commands.TurretDefaultCommand;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final HangerSubsystem m_hangerSubsystem = new HangerSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();

  public Joystick m_driverJoystick = new Joystick(0);
  public Joystick m_operatorJoystick = new Joystick(1);

  
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  //Auto Commands
  private final Command m_noAuto = new PrintCommand("No Auto");

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Add commands to the autonomous command chooser
    m_chooser.setDefaultOption("No Auto", m_noAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);


    Shuffleboard.getTab("Running Commands").add(m_driveSubsystem);
    Shuffleboard.getTab("Running Commands").add(m_intakeSubsystem);
    Shuffleboard.getTab("Running Commands").add(m_hangerSubsystem);
    Shuffleboard.getTab("Running Commands").add(m_shooterSubsystem);
    Shuffleboard.getTab("Running Commands").add(m_turretSubsystem);

    // SmartDashboard.putData(m_driveSubsystem);
    // SmartDashboard.putData(m_intakeSubsystem);
    // SmartDashboard.putData(m_hangerSubsystem);
    // SmartDashboard.putData(m_shooterSubsystem);
    // SmartDashboard.putData(m_turretSubsystem);


    // Configure the button bindings
    configureButtonBindings();

    m_driveSubsystem.setDefaultCommand(
      new DriveDefaultCommand(m_driveSubsystem, () -> m_driverJoystick.getRawAxis(1),
      () -> m_driverJoystick.getRawAxis(4)));

    m_turretSubsystem.setDefaultCommand(
      new TurretDefaultCommand(m_turretSubsystem, () -> m_operatorJoystick.getRawAxis(4)));
    
    m_hangerSubsystem.setDefaultCommand(
      new HangerDefaultCommand(m_hangerSubsystem, () -> m_driverJoystick.getRawAxis(2), () -> m_driverJoystick.getRawAxis(3)));
  


  }



  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    new JoystickButton(m_driverJoystick,5).whenPressed(new DriveShiftHighCommand(m_driveSubsystem));
    new JoystickButton(m_driverJoystick,6).whenPressed(new DriveShiftLowCommand(m_driveSubsystem));

    new JoystickButton(m_driverJoystick,1).whenPressed(new ArmsToggleCommand(m_hangerSubsystem));
    new JoystickButton(m_driverJoystick,4).whenPressed(new EarsToggleCommand(m_hangerSubsystem));


    new JoystickButton(m_operatorJoystick,4).whenPressed(new ShooterSetRPMCommand(m_shooterSubsystem, 1500));
    new JoystickButton(m_operatorJoystick,3).whenPressed(new ShooterSetRPMCommand(m_shooterSubsystem, 1250));
    new JoystickButton(m_operatorJoystick,2).whenPressed(new ShooterSetRPMCommand(m_shooterSubsystem, 1000));
    new JoystickButton(m_operatorJoystick,1).whenPressed(new ShooterStopCommand(m_shooterSubsystem));

    new JoystickButton(m_operatorJoystick,6).whenPressed(new IntakeBallFromGround(m_intakeSubsystem));
    new JoystickButton(m_operatorJoystick,5).whenPressed(new ShooterLoaderForwardCommand(m_shooterSubsystem));


  }




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
    return m_chooser.getSelected();

  }
}
