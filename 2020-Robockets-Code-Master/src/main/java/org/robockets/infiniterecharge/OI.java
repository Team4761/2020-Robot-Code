package org.robockets.infiniterecharge;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public static boolean usingxbox = true; //TODO: Change to an enumeration

    public static Joystick xbox = new Joystick(0);

    public static final OI INSTANCE = new OI();

    private OI() {

    }

    public static OI getInstance() {
        return INSTANCE;
    }

}
