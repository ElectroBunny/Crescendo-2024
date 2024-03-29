// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;

public class AutoShootNote extends Command {
  private Shooter myShooter;
  private Intake intake;
  private double speed;

  public AutoShootNote(double speed) {
    this.speed = speed;
    this.myShooter = Shooter.getInstance();
    this.intake = Intake.getInstance();
    addRequirements(myShooter);
  }

  @Override
  public void initialize() {
    this.myShooter.moveShooter(speed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    this.myShooter.stopShooter();
  }

  @Override
  public boolean isFinished() {
    return !intake.hasGamePiece();  // Runs until the note detaches the limit switch
  }
}
