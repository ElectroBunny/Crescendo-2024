// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class RobotMap {
    // Drive Train
    public static final int DRIVE_LEFT_MASTER  = 7;
    public static final int DRIVE_LEFT_FOLLOWER  = 1;
    public static final int DRIVE_RIGHT_MASTER  = 3;
    public static final int DRIVE_RIGHT_FOLLOWER  = 2;

    public static final int RIGHT_ENCODER_CHANNEL_A = 1;
    public static final int RIGHT_ENCODER_CHANNEL_B = 2;

    public static final int LEFT_ENCODER_CHANNEL_A = 7;
    public static final int LEFT_ENCODER_CHANNEL_B = 8;

    public static final double DRIVE_SHAFT_PERIMETER = 1;
    public static final int TICKS_PER_PULSE = 2048;

    public static final double DRIVE_MOTORS_KV = 0;

    // Shooter
    public static final int SHOOTER_TALON = 20;
    public static final double SHOOTER_SPEED = -1;
    public static final double REVERSED_SHOOTER_SPEED = 0.75;
    public static final double SHOOTER_LOADING_TIME = 1.5;

    public static final int LIMIT_SWITCH_PORT = 0;
  
    // Intake
    public static final int INTAKE_TALON = 21;
    public static final double INTAKE_SPEED = 1;
    // Climber
    public static final int CLIMBER_CONTROLLER = 0;
    public static final double CLIMBER_SPEED = 0.7;

    // Buttons
    public static final int PS4_CONTROLLER = 0;
    public static final int XBOX_CONTROLLER = 0;
    public static final int JOYSTICK_CONTROLLER = 1;

    public static final double ROBOT_WIDTH = 0;  // The distance between the wheels
}
