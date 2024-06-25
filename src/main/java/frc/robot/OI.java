// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.PS4Controller;

/** Add your docs here. */
public class OI 
{
    // Definiton of joystick buttons
    public static final Joystick joystick_controller = new Joystick(RobotMap.JOYSTICK_CONTROLLER);
    public static final JoystickButton button1 = new JoystickButton(joystick_controller, 1);
    public static final JoystickButton button2 = new JoystickButton(joystick_controller, 2);
    public static final JoystickButton button3 = new JoystickButton(joystick_controller, 3);
    public static final JoystickButton button4 = new JoystickButton(joystick_controller, 4);
    public static final JoystickButton button5 = new JoystickButton(joystick_controller, 5);
    public static final JoystickButton button6 = new JoystickButton(joystick_controller, 6);

    // Definition of PS4 controller buttons
    public static final PS4Controller ps4Controller = new PS4Controller(RobotMap.PS4_CONTROLLER);
    public static final JoystickButton L1 = new JoystickButton(ps4Controller, 4);
    public static final JoystickButton R1 = new JoystickButton(ps4Controller, 5);
    
    // definition of the XBOX controller
    public static final Joystick xboxController = new Joystick(RobotMap.XBOX_CONTROLLER);

    /**
     * This function gets the value of the ps4 controller R2 trigger.
     * @return The value of the ps4 controller R2 trigger.
     */
    public static double getPS4RightTriggerAxis(){
        return ps4Controller.getR2Axis();
    }

    /**
     * This function gets the right trigger value of the xbox controller.
     * @return The value of the right trigger of the xbox controller.
     */
    public static double getXBOXRightTriggerAxis(){
        return xboxController.getRawAxis(3);
    }

    /**
     * This function gets the value of the ps4 controller L2 trigger.
     * @return The value of the ps4 controller L2 trigger.
     */
    public static double getPS4LeftTriggerAxis(){
        return ps4Controller.getL2Axis();
    }

    /**
     * This function gets the left trigger value of the xbox controller.
     * @return The value of the left trigger of the xbox controller.
     */
    public static double getXBOXLeftTriggerAxis(){
        return xboxController.getRawAxis(2);
    }

    /**
     * This function gets the X axis from the left joystick of the ps4 controller.
     * @return The X value at the left joystick.
     */
    public static double getPS4LeftX(){
        return ps4Controller.getLeftX();
    }

    /**
     * This function gets the X axis from the left joystick of the XBOX controller.
     * @return The X value at the left joystick.
     */
    public static double getXBOXLeftX(){
        return xboxController.getRawAxis(0);
    }   
    
}
