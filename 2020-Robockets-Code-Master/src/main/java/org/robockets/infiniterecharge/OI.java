package org.robockets.infiniterecharge;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.robockets.infiniterecharge.wheel.RevCounterCommand;
import org.robockets.infiniterecharge.wheel.ToColorNewCommand;

public class OI {

    //arcade drive version
    public static Joystick xbox = new Joystick(0);

    public static JoystickButton aButton = new JoystickButton(xbox, 1);
    public static JoystickButton bButton = new JoystickButton(xbox, 2);
    public static JoystickButton xButton = new JoystickButton(xbox, 3);
    public static JoystickButton yButton = new JoystickButton(xbox, 4);

    public static JoystickButton leftBumper = new JoystickButton(xbox,5);

    //tank drive version
    //public static Joystick flight1 = new Joystick(1); //Yes it COULD go into port 1, but that will cause a runtime error.
    //public static Joystick flight2 = new Joystick(2);

    // Operator 1
    public static Joystick buttonBoard1 = new Joystick(1); //auto

    public static JoystickButton button101 = new JoystickButton(buttonBoard1, 1); //Secondary FlyWheel
    public static JoystickButton button102 = new JoystickButton(buttonBoard1, 2); //polycord-
    public static JoystickButton button103 = new JoystickButton(buttonBoard1, 3); //flywheel-
    public static JoystickButton button104 = new JoystickButton(buttonBoard1, 4); //auto intake
    public static JoystickButton button105 = new JoystickButton(buttonBoard1, 5); //polycord+
    public static JoystickButton button106 = new JoystickButton(buttonBoard1, 6); //control left climber
    public static JoystickButton button107 = new JoystickButton(buttonBoard1, 7); //control right climber
    public static JoystickButton button108 = new JoystickButton(buttonBoard1, 8); //tocolor
    public static JoystickButton button109 = new JoystickButton(buttonBoard1, 9); //revcounter2
    public static JoystickButton button110 = new JoystickButton(buttonBoard1, 10); //shut down revcounter & tocolor
    public static JoystickButton button111 = new JoystickButton(buttonBoard1, 11); //shooter
    public static JoystickButton button112 = new JoystickButton(buttonBoard1, 12);
    public static JoystickButton button113 = new JoystickButton(buttonBoard1, 13); //telescope-
    public static JoystickButton button114 = new JoystickButton(buttonBoard1, 14);
    public static JoystickButton button115 = new JoystickButton(buttonBoard1, 15);
    public static JoystickButton button116 = new JoystickButton(buttonBoard1, 16);
    public static JoystickButton button117 = new JoystickButton(buttonBoard1, 17);
    public static JoystickButton button118 = new JoystickButton(buttonBoard1, 18);
    public static JoystickButton button119 = new JoystickButton(buttonBoard1, 19);
    public static JoystickButton button120 = new JoystickButton(buttonBoard1, 20);
    public static JoystickButton button121 = new JoystickButton(buttonBoard1, 21);
    public static JoystickButton button122 = new JoystickButton(buttonBoard1, 22);
    public static JoystickButton button123 = new JoystickButton(buttonBoard1, 23);

    // Operator 2
    public static Joystick buttonBoard2 = new Joystick(2); //manual

    public static JoystickButton button201 = new JoystickButton(buttonBoard2, 1);
    public static JoystickButton button202 = new JoystickButton(buttonBoard2, 2); //intake-
    public static JoystickButton button203 = new JoystickButton(buttonBoard2, 3); //intake+
    public static JoystickButton button204 = new JoystickButton(buttonBoard2, 4); //reel-
    public static JoystickButton button205 = new JoystickButton(buttonBoard2, 5); //flywheel+
    public static JoystickButton button206 = new JoystickButton(buttonBoard2, 6); //telescope+
    public static JoystickButton button207 = new JoystickButton(buttonBoard2, 7); //wheelarm-
    public static JoystickButton button208 = new JoystickButton(buttonBoard2, 8);
    public static JoystickButton button209 = new JoystickButton(buttonBoard2, 9); //wheel-
    public static JoystickButton button210 = new JoystickButton(buttonBoard2, 10); //reel+
    public static JoystickButton button211 = new JoystickButton(buttonBoard2, 11);
    public static JoystickButton button212 = new JoystickButton(buttonBoard2, 12); //wheel+
    public static JoystickButton button213 = new JoystickButton(buttonBoard2, 13); //wheelarm+
    public static JoystickButton button214 = new JoystickButton(buttonBoard2, 14);
    public static JoystickButton button215 = new JoystickButton(buttonBoard2, 15);
    public static JoystickButton button216 = new JoystickButton(buttonBoard2, 16);
    public static JoystickButton button217 = new JoystickButton(buttonBoard2, 17);
    public static JoystickButton button218 = new JoystickButton(buttonBoard2, 18);
    public static JoystickButton button219 = new JoystickButton(buttonBoard2, 19);
    public static JoystickButton button220 = new JoystickButton(buttonBoard2, 20);
    public static JoystickButton button221 = new JoystickButton(buttonBoard2, 21);
    public static JoystickButton button222 = new JoystickButton(buttonBoard2, 22);
    public static JoystickButton button223 = new JoystickButton(buttonBoard2, 23);

    public static boolean disablebuttoncommands;

    private static final OI INSTANCE = new OI();

    private OI() {
        button109.whenPressed(new RevCounterCommand(0.2, 4));
        button108.whenPressed(new ToColorNewCommand());
    }

    public void periodic() {
        disablebuttoncommands = button110.get();
    }

    public static OI getInstance() {
        return INSTANCE;
    }

}
