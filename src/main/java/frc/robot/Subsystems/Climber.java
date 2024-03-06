// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Climber extends SubsystemBase {
  private static Climber instance = null;
  private WPI_TalonSRX climberMotor;

  public Climber() {
    // Define climber controller
    this.climberMotor = new WPI_TalonSRX(RobotMap.CLIMBER_TALON);
    this.climberMotor.configFactoryDefault();

    this.climberMotor.setInverted(false);

    this.climberMotor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This function moves the climber motor according to a given speed.
   * @param gain The speed to move the motor in(between -1 to 1)
   */
  public void moveClimber(double gain)
  {
    this.climberMotor.set(gain);
    SmartDashboard.putNumber("Climber Voltage", climberMotor.getBusVoltage());  //Climber motor vault gets
    SmartDashboard.putNumber("Climber Current", climberMotor.getSupplyCurrent());  //Climber motor current gets
  }

  /**
   * This function stops the climber.
   */
  public void stopClimber()
  {
    this.climberMotor.stopMotor();
  }

  /**
   * This function returns the instance of the climber.
   * @return The instance of the climber.
   */
  public static Climber getInstance()
  {
    if(instance == null)
    {
      instance = new Climber();
    }

    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
