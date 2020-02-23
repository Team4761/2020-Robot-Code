package org.robockets.infiniterecharge.wheel;

import com.fasterxml.jackson.core.io.IOContext;
import com.revrobotics.ColorMatch;
import org.robockets.infiniterecharge.Robot;

import java.util.ArrayList;

import static org.robockets.infiniterecharge.wheel.WheelSubsystem.*;

public class ColorSensorEncoder {

    // {"Red","Yellow","Blue","Green","Unknown"}
    //private int[] colorenum = {0,1,2,3,4};

    private int[] colorAverage;

    private int accuracy;
    private int loopcounter = 0;

    private int red = 0, blue = 0, green = 0, yellow = 0, unknown = 0, max;



    public ColorSensorEncoder(int samplesize) {
        this.accuracy = samplesize;
        colorAverage = new int[samplesize];
    }

    public int calculateAverageColor() {

        int prevcol = colorAverage[loopcounter%accuracy];

        if(prevcol == 0) red--;
        if(prevcol == 3) green--;
        if(prevcol == 1) yellow--;
        if(prevcol == 2) blue--;
        if(prevcol == 4) unknown--;

        int col = Robot.Wheel.getColor();
        colorAverage[loopcounter%accuracy] = col;

        if(col == 0) red++;
        if(col == 3) green++;
        if(col == 1) yellow++;
        if(col == 2) blue++;
        if(col == 4) unknown++;

        max = Math.max(red, Math.max(green, Math.max(blue, Math.max(yellow, unknown))));

        loopcounter++;
        if(loopcounter == accuracy+2) loopcounter=0;

        if (max == red) return 0;
        if (max == green) return 3;
        if (max == blue) return 2;
        if (max == yellow) return 1;
        else return 4;
    }

    /*public double calculateRevs() { //todo: update to be more accurate


        double col = Robot.Wheel.oncolor;
        if(col == 0) hasred = true;
        if(col == 3) hasgreen = true;
        if(col == 2) hasblue = true;
        if(col == 1) hasyellow = true;

        if(hasred) revs+= 0.125;
        if(hasblue) revs+= 0.125;
        if(hasgreen) revs+= 0.125;
        if(hasyellow) revs+= 0.125;

        if(hasred && hasblue && hasgreen && hasyellow) {
            hasblue = false;
            hasred = false;
            hasgreen = false;
            hasyellow = false;
        }
        return revs;
    }*/

    private int oldcol;
    private double revs;
    public double calculateRevs() {

        int col = Robot.Wheel.oncolor;

        if(col != oldcol) revs +=0.125;

        oldcol = col;

        return revs;
    }

    public int[] getSample() {
        return colorAverage;
    }

    public String getColorString() {
        if(max == red) return "Red";
        if(max == blue) return "Blue";
        if(max == green) return "Green";
        if(max == yellow) return "Yellow";
        else return "Unknown";
    }
}
