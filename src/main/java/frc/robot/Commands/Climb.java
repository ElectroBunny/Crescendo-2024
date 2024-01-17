// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Climber;

public class Climb extends Command {
  private double speed;
  private Climber myClimber;
  
  /** Creates a new Climb. */
  public Climb(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.myClimber = Climber.getInstance();
    addRequirements(myClimber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.myClimber.moveClimber(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.myClimber.stopClimber();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
