// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


public class Intake extends SubsystemBase {
  private static Intake instance = null;

  private WPI_VictorSPX masterVictor;
  private WPI_VictorSPX followerVictor;

  /** Creates a new Intake. */
  public Intake() {
    this.masterVictor = new WPI_VictorSPX(RobotMap.INTAKE_MASTER);
    this.followerVictor = new WPI_VictorSPX(RobotMap.INTAKE_FOLLOWER);

    followerVictor.follow(masterVictor); // setting the followerVictor to follow the masterVictor
  }

  /**
   * This function moves the voltage to the Victor_SPX motors
   * @param speed
   */
  public void moveIntake(double speed)
  {
    masterVictor.set(speed);
  }


  /** 
   * This function stops the voltage to the Victor_SPX motors
   */
  public void stopIntake()
  {
    masterVictor.stopMotor();
  }

  /**
   * This function checks if there is an Intake object, if there is it returns it and if not it creates a new one
   * @return the instance of the Intake
   */
  public static Intake getInstance()
  {
    if(instance == null)
    {
      instance = new Intake();
    }

    return instance;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
