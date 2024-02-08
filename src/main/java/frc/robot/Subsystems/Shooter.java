// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX shootingVictor;

  private static Shooter instance = null;

  public Shooter()
  {
    this.shootingVictor = new WPI_TalonSRX(RobotMap.SHOOTER_TALON);

    this.shootingVictor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This function moves the shooting motor according to a given speed.
   * @param gain The speed to move the motor in(between -1 to 1)
   */
  public void moveShooter(double gain)
  {
    this.shootingVictor.set(gain);
    SmartDashboard.putNumber("Shooter Voltage", shootingVictor.getBusVoltage());//shooting motor vault gets
    SmartDashboard.putNumber("Shooter Current", shootingVictor.getSupplyCurrent());//shooting motor current gets
  }


  /**
   * This function stops the shooting motor.
   */
  public void stopShooter()
  {
    this.shootingVictor.stopMotor();
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
