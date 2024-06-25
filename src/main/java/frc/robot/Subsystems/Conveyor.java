// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Conveyor extends SubsystemBase {
  private WPI_TalonSRX conveyorVictor;

  private static Conveyor instance = null;

  public Conveyor()
  {
    this.conveyorVictor = new WPI_TalonSRX(RobotMap.CONVEYOR_TALON);

    this.conveyorVictor.setNeutralMode(NeutralMode.Brake);
    // this.conveyorVictor.configSupplyCurrentLimit(RobotMap.SHOOTER_SUPPLY_LIMIT);
  }

  /**
   * This function moves the Conveyor motor according to a given speed.
   * @param gain The speed to move the motor in(between -1 to 1)
   */
  public void moveConveyor(double gain)
  {
    this.conveyorVictor.set(gain);
    SmartDashboard.putNumber("Conveyor Voltage", conveyorVictor.getMotorOutputVoltage());//shooting motor vault gets
    SmartDashboard.putNumber("Conveyor Current", conveyorVictor.getSupplyCurrent());//shooting motor current gets
  }


  /**
   * This function stops the Conveyor motor.
   */
  public void stopConveyor()
  {
    this.conveyorVictor.stopMotor();
  }

  /**
   * This function checks if the instance is null and creates a new instance
   * @return
   */
  public static Conveyor getInstance()
  {
    if(instance == null)
    {
      instance = new Conveyor();
    }

    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
