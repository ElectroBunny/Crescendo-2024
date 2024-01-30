// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Intake;

public class CollectNote extends Command {

  private Intake myIntake;
  private double speed;

  public CollectNote(double speed) 
  {
    this.speed = speed;
    this.myIntake = Intake.getInstance();
    addRequirements(myIntake);
  }

  @Override
  public void initialize() 
  {
    this.myIntake.moveIntake(speed);
  }

  @Override
  public void execute() 
  {

  }

  @Override
  public void end(boolean interrupted)
  {
    this.myIntake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
