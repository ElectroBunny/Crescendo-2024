// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Intake;

public class AutoCollectNote extends Command {
  private Intake intake;
  private double speed;

  public AutoCollectNote(double speed) 
  {
    this.speed = speed;
    this.intake = Intake.getInstance();
    addRequirements(intake);
  }

  @Override
  public void initialize() 
  {
    this.intake.moveIntake(speed);
  }

  @Override
  public void execute() 
  {

  }

  @Override
  public void end(boolean interrupted)
  {
    this.intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return intake.hasGamePiece();  // It will run until the note touches the switch
  }
}
