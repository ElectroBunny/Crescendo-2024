// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.ArcadeDrive;
// import frc.robot.Commands.Climb;
import frc.robot.Commands.CollectNote;
import frc.robot.Commands.ShootNote;
import frc.robot.Subsystems.DriveTrain;

public class RobotContainer {
  private final DriveTrain driveTrain;
  
  private final SendableChooser<Command> autoChooser;

  private CvSink cvSink;
  private CvSource outputStream;
  
  public RobotContainer() {
    driveTrain = DriveTrain.getInstance();

    configureButtonBindings();
    registerAutoCommands();

    autoChooser = AutoBuilder.buildAutoChooser("Default auto");
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void registerAutoCommands() {
    // Auto collect command
    NamedCommands.registerCommand("AutoCollectNote", Commands.deadline(
      new CollectNote(RobotMap.INTAKE_SPEED),
      new WaitCommand(RobotMap.AUTO_COLLECT_TIME)));
    
    // Auto shoot command
    NamedCommands.registerCommand("AutoShootNote", Commands.deadline(
      new ShootNote(RobotMap.SHOOTER_SPEED),
      new WaitCommand(RobotMap.AUTO_SHOOT_TIME)));
  }

  public void logInitialize()
  {
    DataLogManager.start();
    DriverStation.startDataLog(DataLogManager.getLog());
  }

  private void configureButtonBindings() 
  {
    driveTrain.setDefaultCommand(new ArcadeDrive(() -> OI.getPS4LeftTriggerAxis(),
    () -> OI.getPS4RightTriggerAxis(), () -> OI.getPS4LeftX()));
    
    // Button for loading the shooter and conveying the note
    // OI.button1.whileTrue(new ShootNote(RobotMap.SHOOTER_SPEED).alongWith(
    //   Commands.sequence(
    //   new WaitCommand(RobotMap.SHOOTER_LOADING_TIME),
    //   new CollectNote(RobotMap.INTAKE_SPEED)
    //   )
    // ));
    
    OI.button1.whileTrue(new ShootNote(RobotMap.SHOOTER_SPEED));
    OI.button2.whileTrue(new ShootNote(RobotMap.REVERSED_SHOOTER_SPEED));

    OI.button3.whileTrue(new CollectNote(RobotMap.INTAKE_SPEED));
    OI.button5.whileTrue(new CollectNote(-RobotMap.INTAKE_SPEED));

    OI.button6.onTrue(new InstantCommand(() -> driveTrain.resetPose(new Pose2d(0, 0, new Rotation2d(0)))));

    // OI.button4.whileTrue(new Climb(RobotMap.CLIMBER_SPEED));
    // OI.button6.whileTrue(new Climb(-RobotMap.CLIMBER_SPEED));
  }

  public void onAutoInit() {
    driveTrain.resetEncoders();
  }

  public void startCamera(){
    CameraServer.startAutomaticCapture();
    cvSink = CameraServer.getVideo();
    outputStream = CameraServer.putVideo("Blur", 640, 480);
  }

  public void backupAutonomus() {
    double startTime = Timer.getFPGATimestamp();

    while (Timer.getFPGATimestamp() - startTime < 3){
      driveTrain.arcadeDrive(-0.5
      , 0);
      
      if(Timer.getFPGATimestamp() - startTime >= 15){ 
        return;
      }
    }
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
