// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Climber;

public class Climb extends Command {
  private double speed;
  private Climber myClimber;
  
  public Climb(double speed) {
    this.speed = speed;
    this.myClimber = Climber.getInstance();
    addRequirements(myClimber);
  }

  @Override
  public void initialize() {
    this.myClimber.moveClimber(speed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    this.myClimber.stopClimber();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
