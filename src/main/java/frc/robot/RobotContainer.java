// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.Commands.ArcadeDrive;
import frc.robot.Commands.Climb;
import frc.robot.Commands.CollectNotes;
import frc.robot.Commands.ConveyNote;
import frc.robot.Subsystems.DriveTrain;

public class RobotContainer {
  private final DriveTrain driveTrain;
  
  public RobotContainer() {
    driveTrain = DriveTrain.getInstance();

    configureButtonBindings();
  }

  private void configureButtonBindings() 
  {
    driveTrain.setDefaultCommand(new ArcadeDrive(() -> OI.getPS4RightTriggerAxis(),
    () -> OI.getPS4LeftTriggerAxis(), () -> OI.getPS4LeftX()));

    OI.button1.whileTrue(new ConveyNote(0.8));
    OI.button2.whileTrue(new ConveyNote(-0.8));
    
    OI.button3.whileTrue(new CollectNotes(0.8));
    OI.button4.whileTrue(new CollectNotes(-0.8));

    OI.button5.whileTrue(new Climb(0.5));
    OI.button6.whileTrue(new Climb(-0.5));
  }
}
