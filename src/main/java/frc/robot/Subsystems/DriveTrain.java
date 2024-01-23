// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  private WPI_TalonSRX rightMaster;
  private WPI_TalonSRX rightFollower;
  private WPI_TalonSRX leftMaster;
  private WPI_TalonSRX leftFollower;

  private DifferentialDrive diffDrive;

  private static DriveTrain instance = null;
  private ADXRS450_Gyro gyro;

  private DifferentialDriveKinematics kinematics;

  private DifferentialDriveOdometry odometry;

  private Encoder leftEncoder;
  private Encoder rightEncoder;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    this.rightMaster = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_MASTER);
    this.rightFollower = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_FOLLOWER);
    this.leftMaster = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_MASTER);
    this.leftFollower = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_FOLLOWER);

    this.rightMaster.configFactoryDefault();
    this.rightFollower.configFactoryDefault();
    this.leftMaster.configFactoryDefault();
    this.leftFollower.configFactoryDefault();

    // Decleration of opposite directions.
    this.rightMaster.setInverted(false);
    this.rightFollower.setInverted(false);
    this.leftMaster.setInverted(true);
    this.leftFollower.setInverted(true);  

    this.rightMaster.setNeutralMode(NeutralMode.Brake);
    this.rightFollower.setNeutralMode(NeutralMode.Brake);
    this.leftMaster.setNeutralMode(NeutralMode.Brake);
    this.leftFollower.setNeutralMode(NeutralMode.Brake);

    // Joining masters and followers motor controllers.
    this.rightFollower.follow(this.rightMaster);
    this.leftFollower.follow(this.leftMaster);

    this.diffDrive = new DifferentialDrive(this.leftMaster, this.rightMaster);

    // Creates the gyro
    this.gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    this.gyro.calibrate();

    // Creates the encoders
    this.rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B, true, EncodingType.k2X);
    this.rightEncoder.setDistancePerPulse(RobotMap.DRIVE_SHAFT_PERIMETER / RobotMap.TICKS_PER_PULSE);
    this.rightEncoder.reset();

    this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, false, EncodingType.k2X);
    this.leftEncoder.setDistancePerPulse(RobotMap.DRIVE_SHAFT_PERIMETER / RobotMap.TICKS_PER_PULSE);
    this.leftEncoder.reset();

    this.kinematics = new DifferentialDriveKinematics(RobotMap.ROBOT_WIDTH);

    // Creates the odometry
    this.odometry = new DifferentialDriveOdometry(
      gyro.getRotation2d(),
      leftEncoder.getDistance(), rightEncoder.getDistance(),
      new Pose2d(0, 0, new Rotation2d()));
  }

    /**
     * This function moves the robot according to given values it gets(the value in each moving axis).
     * @param forward The robot's speed along the X axis(percentage).
     * @param turn The robot's rotation rate around the Z axis(percentage).
     */
    public void arcadeDrive(double forward, double turn){
      // Deadband
      if(Math.abs(forward) < 0.05)
        forward = 0.0;
  
      if(Math.abs(turn) < 0.05)
        turn = 0.0;
      
      this.diffDrive.arcadeDrive(forward, turn);
    }

    /**
     * This function stops the motors of the Drive Train.
     */
    public void stopMotors() {
      this.rightMaster.stopMotor();
      this.leftMaster.stopMotor();
    }

    /**
     * This function gets the object of the DriveTrain system if exists, and if not it creates a new one.
     * @return The only object of the DriveTrain system.
     */
    public static DriveTrain getInstance() {
      if (instance == null) {
        instance = new DriveTrain();
      }
      return instance;
    }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Gyro", gyro.getAngle());

    odometry.update(this.gyro.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
  }
}
