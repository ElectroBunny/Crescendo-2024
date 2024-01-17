// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.Commands.CollectNotes;
import frc.robot.Commands.ConveyNote;

public class RobotContainer {
  private final OI oi;

  public RobotContainer() {
    oi = new OI();
    configureButtonBindings();
  }

  private void configureButtonBindings() 
  {
    oi.button1.whileTrue(new ConveyNote(0.8));
    oi.button2.whileTrue(new ConveyNote(-0.8));
    oi.button3.whileTrue(new CollectNotes(0.8));
    oi.button4.whileTrue(new CollectNotes(-0.8));
  }
}
