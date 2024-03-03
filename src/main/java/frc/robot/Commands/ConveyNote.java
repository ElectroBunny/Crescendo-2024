// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Conveyor;

public class ConveyNote extends Command {
  private Conveyor myConveyor;
  private double speed;

  public ConveyNote(double speed) {
    this.speed = speed;
    this.myConveyor = Conveyor.getInstance();
    addRequirements(myConveyor);
  }

  @Override
  public void initialize() {
    this.myConveyor.moveConveyor(speed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    this.myConveyor.stopConveyor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
