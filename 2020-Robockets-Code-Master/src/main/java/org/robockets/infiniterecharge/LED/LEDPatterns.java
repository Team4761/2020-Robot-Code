package org.robockets.infiniterecharge.LED;

import org.robockets.infiniterecharge.RobotMap;

public class LEDPatterns {

    private static int counter = 0;

    private static void end() {
        counter++;
        counter %= RobotMap.ledBuffer.getLength();
        RobotMap.led.setData(RobotMap.ledBuffer); //this gives you an error b/c there's no actual LED connected rn.
    }

    public static void pattern(int red, int green, int blue, double brightness, int blinkspeed) {
        for (int i = 0; i < RobotMap.ledBuffer.getLength(); i++) {
            if(blinkspeed/2.0 >= counter%blinkspeed && counter%blinkspeed >= 0) RobotMap.ledBuffer.setRGB(i, (int)(red*brightness), (int)(green*brightness), (int)(blue*brightness));
            else RobotMap.ledBuffer.setRGB(i,0,0,0);
        }
        end();
    }

    public static void rainbow() {
        for(int i = 0; i < RobotMap.ledBuffer.getLength(); i++) {
            final var hue = (counter%180 + (i * 180 / RobotMap.ledBuffer.getLength())) % 180;
            // Set the value
            RobotMap.ledBuffer.setHSV(i, hue, 255, 128);
        }
        end();
    }

    public static void red(double brightness, int blinkspeed) {
        pattern(255,0,0,brightness,blinkspeed);
        end();
    }

    public static void green(double brightness, int blinkspeed) {
        pattern(0,255,0,brightness,blinkspeed);
        end();
    }

    public static void blue(double brightness, int blinkspeed) {
        pattern(0,0,255,brightness,blinkspeed);
        end();
    }

    public static void colorflow(int red, int green, int blue, int flowlength) {
        int cut = 255/flowlength;
        for (int i = 0; i < RobotMap.ledBuffer.getLength(); i++) {
            RobotMap.ledBuffer.setRGB((int)(i+counter)% RobotMap.ledBuffer.getLength(),red*(i%flowlength)*cut,green*(i%flowlength)*cut,blue*(i%flowlength)*cut);
        }
        end();
    }
}

