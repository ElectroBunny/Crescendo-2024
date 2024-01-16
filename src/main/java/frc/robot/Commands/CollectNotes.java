// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Intake;

public class CollectNotes extends Command {
  
  private Intake myIntake;
  private double speed;

  public CollectNotes(double speed) 
  {
    this.speed = speed;
    this.myIntake = Intake.getInstance();
    addRequirements(myIntake);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    this.myIntake.moveIntake(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    this.myIntake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
