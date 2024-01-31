// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.ArcadeDrive;
import frc.robot.Commands.Climb;
import frc.robot.Commands.CollectNote;
import frc.robot.Commands.ConveyNote;
import frc.robot.Commands.ShootNote;
import frc.robot.Subsystems.DriveTrain;

public class RobotContainer {
  private final DriveTrain driveTrain;
  
  private final SendableChooser<Command> autoChooser;
  
  public RobotContainer() {
    driveTrain = DriveTrain.getInstance();

    configureButtonBindings();

    autoChooser = AutoBuilder.buildAutoChooser("Default auto");
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureButtonBindings() 
  {
    driveTrain.setDefaultCommand(new ArcadeDrive(() -> OI.getPS4RightTriggerAxis(),
    () -> OI.getPS4LeftTriggerAxis(), () -> OI.getPS4LeftX()));
    
    OI.button1.whileTrue(new CollectNote(RobotMap.INTAKE_SPEED));
    OI.button2.whileTrue(new CollectNote(-RobotMap.INTAKE_SPEED));

    OI.button3.whileTrue(new Climb(RobotMap.CLIMBER_SPEED));
    OI.button4.whileTrue(new Climb(-RobotMap.CLIMBER_SPEED));

    // Button for loading the shooter and conveying the note
    OI.button5.whileTrue(new ShootNote(RobotMap.SHOOTER_SPEED).alongWith(
      Commands.sequence(
      new WaitCommand(RobotMap.SHOOTER_LOADING_TIME),
      new ConveyNote(RobotMap.INTAKE_SPEED)
      )
    ));


    OI.button6.whileTrue(new ShootNote(-RobotMap.SHOOTER_SPEED));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
