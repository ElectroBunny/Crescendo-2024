package frc.robot.Utilities;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
    private DigitalInput limitSwitch;

    public LimitSwitch(int switchPort) {
        limitSwitch = new DigitalInput(switchPort);
    }

    public boolean getSwitchState() {
        return limitSwitch.get();
    }
}
