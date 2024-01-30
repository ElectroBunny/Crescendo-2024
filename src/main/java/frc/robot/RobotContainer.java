// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Commands.ArcadeDrive;
import frc.robot.Commands.Climb;
import frc.robot.Commands.CollectNote;
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
    
    OI.button1.whileTrue(new CollectNote(0.8));
    OI.button2.whileTrue(new CollectNote(-0.8));

    OI.button3.whileTrue(new Climb(0.5));
    OI.button4.whileTrue(new Climb(-0.5));

    OI.button5.whileTrue(new ShootNote(0.5));
    OI.button6.whileTrue(new ShootNote(-0.5));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
