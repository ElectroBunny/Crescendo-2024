// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class OI 
{
    //Definiton of joystick buttons
    
    public Joystick joystick_controller = new Joystick(RobotMap.JOYSTICK_CONTROLLER);
    public JoystickButton button1 = new JoystickButton(joystick_controller, 1);
    public JoystickButton button2 = new JoystickButton(joystick_controller, 2);
}
