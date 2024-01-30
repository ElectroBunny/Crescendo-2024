// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter;


public class ShootNote extends Command {

  private Shooter myShooter;
  private double speed;

  public ShootNote(double speed) {
    this.speed = speed;
    this.myShooter = Shooter.getInstance();
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
    return false;
  }
}
