// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
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

  private DifferentialDriveWheelSpeeds wheelSpeeds;

  private double leftSpeed;
  private double rightSpeed;

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
    this.rightEncoder.setDistancePerPulse(RobotMap.DRIVE_WHEEL_PERIMETER / RobotMap.ENCODER_TICKS_PER_PULSE);
    this.rightEncoder.reset();

    this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, false, EncodingType.k2X);
    this.leftEncoder.setDistancePerPulse(RobotMap.DRIVE_WHEEL_PERIMETER / RobotMap.ENCODER_TICKS_PER_PULSE);
    this.leftEncoder.reset();

    this.kinematics = new DifferentialDriveKinematics(RobotMap.ROBOT_WIDTH);

    // Creates the odometry
    this.odometry = new DifferentialDriveOdometry(
      gyro.getRotation2d(),
      leftEncoder.getDistance(), rightEncoder.getDistance(),
      new Pose2d(0, 0, new Rotation2d()));

      // Creates the AutoBuilder
      AutoBuilder.configureRamsete(
                this::getPose2d,
                this::resetPose,
                this::getRobotSpeed,
                this::drive, // Method that will drive the robot given ChassisSpeeds
                new ReplanningConfig(),
                () -> {
                    // Boolean supplier that controls when the path will be mirrored for the red alliance
                    // This will flip the path being followed to the red side of the field.
                    // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                    var alliance = DriverStation.getAlliance();
                    if (alliance.isPresent()) {
                        return alliance.get() == DriverStation.Alliance.Red;
                    }
                    return false;
                },
                this // Reference to this subsystem to set requirements
        );
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

    private Pose2d getPose2d() {
      return this.odometry.getPoseMeters();
    }

    private Rotation2d getRotation2d() {
      return gyro.getRotation2d();
    }

    private void resetPose(Pose2d pose) {
      this.odometry.resetPosition(getRotation2d(), getLeftDistance(), getRightDistance(), pose);
    }

    private double getLeftDistance() {
      return leftEncoder.getDistance();
    }

    private double getRightDistance() {
      return rightEncoder.getDistance();
    }

    private DifferentialDriveWheelSpeeds getSidesSpeeds() {
      return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
    }

    private ChassisSpeeds getRobotSpeed() {
      return this.kinematics.toChassisSpeeds(getSidesSpeeds());
    }

    private void drive(ChassisSpeeds speed) {
      this.wheelSpeeds = this.kinematics.toWheelSpeeds(speed);

      this.rightSpeed = wheelSpeeds.rightMetersPerSecond;
      this.leftSpeed = wheelSpeeds.leftMetersPerSecond;

      this.rightMaster.set(rightSpeed * RobotMap.DRIVE_MOTORS_KV);
      this.leftMaster.set(leftSpeed * RobotMap.DRIVE_MOTORS_KV);
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

    private void logMotors()
    {
      SmartDashboard.putNumber("RightMaster Voltage", rightMaster.getBusVoltage());  //rightMaster motor volt gets
      SmartDashboard.putNumber("RightMaster Current", rightMaster.getSupplyCurrent());  //rightMaster motor current gets

      SmartDashboard.putNumber("LeftMaster Voltage", leftMaster.getBusVoltage());  //leftMaster motor volt gets
      SmartDashboard.putNumber("LeftMaster Current", leftMaster.getSupplyCurrent());  //leftMaster motor current gets

      SmartDashboard.putNumber("RightFollower Voltage", rightFollower.getBusVoltage());  //rightFollower motor volt gets
      SmartDashboard.putNumber("RightFollower Current", rightFollower.getSupplyCurrent());  //rightFollower motor current gets

      SmartDashboard.putNumber("LeftFollower Voltage", leftFollower.getBusVoltage());  //leftFollower motor volt gets
      SmartDashboard.putNumber("LeftFollower Current", leftFollower.getSupplyCurrent());  //leftFollower motor current gets
    }

    private void logPosition()
    {
      SmartDashboard.putNumber("X", this.odometry.getPoseMeters().getX()); // gets x position of robot
      SmartDashboard.putNumber("Y", this.odometry.getPoseMeters().getY()); // gets y position of robot
    }
    
    private void logEncoder()
    {
      SmartDashboard.putNumber("Distance Right Encoder", getRightDistance());
      SmartDashboard.putNumber("Distance Left Encoder", getLeftDistance());
    }

  @Override
  public void periodic() {
    odometry.update(this.gyro.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
    SmartDashboard.putNumber("Gyro", gyro.getAngle());
    logMotors();
    logPosition();
    logEncoder();
  }
}
