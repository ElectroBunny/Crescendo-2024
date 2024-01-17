// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private WPI_VictorSPX conveyor;
  private WPI_VictorSPX releaser;

  private static Shooter instance = null;

  public Shooter() 
  {
    this.conveyor = new WPI_VictorSPX(RobotMap.SHOOTER_CONVEYOR);
    this.releaser = new WPI_VictorSPX(RobotMap.SHOOTER_RELEASER);

    this.conveyor.setNeutralMode(NeutralMode.Brake);
    this.releaser.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This function moves the conveyor motor according to a given speed.
   * @param gain The speed to move the motor in(between -1 to 1)
   */
  public void moveConveyor(double gain)
  {
    this.conveyor.set(gain);
  }

  /**
   * This function moves the releaser according to a given speed.
   * @param gain The speed to move the motor in(between -1 to 1)
   */
  public void moveReleaser(double gain)
  {
    this.releaser.set(gain);
  }

  /**
   * This function stops the conveyor.
   */
  public void stopConveyor()
  {
    this.conveyor.stopMotor();
  }

  /**
   * This function stops the releaser.
   */
  public void stopReleaser()
  {
    this.releaser.stopMotor();
  }

  /**
   * This function checks if the instance is null and creates a new instance
   * @return
   */
  public static Shooter getInstance()
  {
    if(instance == null)
    {
      instance = new Shooter();
    }

    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
