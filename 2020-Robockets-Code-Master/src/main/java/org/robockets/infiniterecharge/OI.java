package org.robockets.infiniterecharge;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.robockets.infiniterecharge.climber.reelIn;
import org.robockets.infiniterecharge.climber.reelOut;
import org.robockets.infiniterecharge.climber.telescopeIn;
import org.robockets.infiniterecharge.climber.telescopeOut;
import org.robockets.infiniterecharge.wheel.MovePistonCommand;

public class OI {
    public static boolean usingxbox = true; //TODO: Change to an enumeration

    //arcade drive version
    public static Joystick xbox = new Joystick(0);

    public static JoystickButton a = new JoystickButton(xbox, 0);
    public static JoystickButton b = new JoystickButton(xbox, 1);
    public static JoystickButton x = new JoystickButton(xbox, 2);
    public static JoystickButton y = new JoystickButton(xbox, 3);


    //tank drive version
    public static Joystick flight1 = new Joystick(1); //Yes it COULD go into port 1, but that will cause a runtime error.
    public static Joystick flight2 = new Joystick(2);

    //Button board?
    public static Joystick buttonboard1 = new Joystick(3); //TODO: verify port numbers

    public static JoystickButton b100 = new JoystickButton(buttonboard1,0);
    public static JoystickButton b101 = new JoystickButton(buttonboard1, 1);
    public static JoystickButton b102 = new JoystickButton(buttonboard1, 2);



    public static final OI INSTANCE = new OI();

    private OI() {
        b100.whenPressed(new MovePistonCommand(true)); //TODO: Simplify this down to 1 button
        b101.whenPressed(new MovePistonCommand(false));
        a.whenPressed(new telescopeIn());
        b.whenPressed(new reelIn());
        x.whenPressed(new telescopeOut());
        y.whenPressed(new reelOut());


    }

    public static OI getInstance() {
        return INSTANCE;
    }

}
