// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private WPI_TalonSRX intakeVictor;

  private DigitalInput limitSwitch;

  private static Intake instance = null;

  public Intake() {
    this.intakeVictor = new WPI_TalonSRX(RobotMap.INTAKE_TALON);

    this.limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_PORT);

    this.intakeVictor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This function moves the intake according to a given speed.
   * @param speed The speed to move the intake in(between -1 to 1).
   */
  public void moveIntake(double speed)
  {
    intakeVictor.set(speed);
    SmartDashboard.putNumber("Intake Voltage", intakeVictor.getBusVoltage());//Intake motor vault gets
    SmartDashboard.putNumber("Intake Current", intakeVictor.getSupplyCurrent());//Intake motor current gets
  }


  /** 
   * This function stops the intake.
   */
  public void stopIntake()
  {
    intakeVictor.stopMotor();
  }

  /**
   * This function checks if there is an Intake object, if there is it returns it and if not it creates a new one.
   * @return The instance of the Intake Subsystem.
   */
  public static Intake getInstance()
  {
    if(instance == null)
    {
      instance = new Intake();
    }

    return instance;
  }

  public boolean hasGamePiece() {
    SmartDashboard.putBoolean("LimitSwitch", this.limitSwitch.get());
    return this.limitSwitch.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
