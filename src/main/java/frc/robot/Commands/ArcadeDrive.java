// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveTrain;

public class ArcadeDrive extends Command {
  private DriveTrain innerDrive;

  private DoubleSupplier forwardSupplier, backwardSupplier, turnSupplier;

  public ArcadeDrive(DoubleSupplier forwardSupplier, DoubleSupplier backwardSupplier,
   DoubleSupplier turnSupplier) {
    // Saves the suppliers for the arcade drive
    this.forwardSupplier = forwardSupplier;
    this.backwardSupplier = backwardSupplier;
    this.turnSupplier = turnSupplier;

    this.innerDrive = DriveTrain.getInstance();
    addRequirements(innerDrive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    innerDrive.arcadeDrive(forwardSupplier.getAsDouble() - backwardSupplier.getAsDouble(),
     turnSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    innerDrive.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}