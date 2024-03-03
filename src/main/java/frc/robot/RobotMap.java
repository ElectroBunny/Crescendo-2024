// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

public final class RobotMap {
    // Drive Train
    public static final int DRIVE_LEFT_MASTER  = 21;
    public static final int DRIVE_LEFT_FOLLOWER  = 3;
    public static final int DRIVE_RIGHT_MASTER  = 1;
    public static final int DRIVE_RIGHT_FOLLOWER  = 7;

    public static final int RIGHT_ENCODER_CHANNEL_A = 1;
    public static final int RIGHT_ENCODER_CHANNEL_B = 2;

    public static final int LEFT_ENCODER_CHANNEL_A = 7;
    public static final int LEFT_ENCODER_CHANNEL_B = 8;

    public static final double INCH_IN_CM = 2.54;
    public static final double DRIVE_WHEEL_DIAMETER = 8 * INCH_IN_CM * 0.01;  // Saves in meters
    public static final double DRIVE_WHEEL_PERIMETER = Math.PI * DRIVE_WHEEL_DIAMETER;
    public static final int ENCODER_TICKS_PER_PULSE = 2048;

    public static final double DRIVE_MOTORS_KV = 0;

    public static final double SINGLE_DRIVE_SIDE_SPEED = 0.4;

    public static final SupplyCurrentLimitConfiguration DRIVE_SUPPLY_LIMIT = new SupplyCurrentLimitConfiguration(true, 38, 38, 0.01);
    public static final SupplyCurrentLimitConfiguration SHOOTER_SUPPLY_LIMIT = new SupplyCurrentLimitConfiguration(true, 40, 40, 0.01);


    // Shooter
    public static final int SHOOTER_TALON = 20;
    public static final double REVERSED_SHOOTER_SPEED = -0.6;
    public static final double SHOOTER_SPEED = 0.8;
    public static final double AUTO_SHOOT_TIME = 1.5;
    public static final double BOOSTED_SHOOTER_SPEED = 0.6;

    public static final int LIMIT_SWITCH_PORT = 0;
  
    // Intak
    public static final int INTAKE_TALON = 2;
    public static final double AUTO_COLLECT_TIME = 2.5;
    public static final double INTAKE_SPEED = -1;
  
    // Climber
    public static final int CLIMBER_CONTROLLER = 0;
    public static final double CLIMBER_SPEED = 0.7;

    // Buttons
    public static final int PS4_CONTROLLER = 0;
    public static final int XBOX_CONTROLLER = 0;
    public static final int JOYSTICK_CONTROLLER = 1;

    // Autonomus
    public static final double ROBOT_WIDTH = 0.58;  // The distance between the wheels
    public static final double BACKUP_AUTO_DRIVE_TIME = 3;
    public static final double BACKUTP_AUTO_DRIVE_SPEED = -0.5;
    public static final double MAX_AUTO_TIME = 15;
}
