package org.robockets.infiniterecharge.wheel;

import com.fasterxml.jackson.core.io.IOContext;
import com.revrobotics.ColorMatch;
import org.robockets.infiniterecharge.Robot;

import java.util.ArrayList;

import static org.robockets.infiniterecharge.wheel.WheelSubsystem.*;

public class ColorSensorEncoder {

    // {"Red","Yellow","Blue","Green","Unknown"}
    //private int[] colorenum = {0,1,2,3,4};

    private final ColorMatch m_colorMatcher;
    private ArrayList<Integer> colorAverage;

    private int accuracy;
    private int loopcounter = 0;

    private int red = 0, blue = 0, green = 0, yellow = 0, unknown = 0, max;

    private double revs = 0.0;

    public ColorSensorEncoder(int samplesize) {
        this.accuracy = samplesize;
        m_colorMatcher = new ColorMatch();
        colorAverage = new ArrayList<>();
        init();
    }

    private void init() {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

        for(int i = 0; i < accuracy++; i++) colorAverage.add(4);
    }

    public int calculateAverageColor() {

        int prevcol = colorAverage.get(loopcounter%accuracy);

        if(prevcol == 0) red--;
        if(prevcol == 3) green--;
        if(prevcol == 1) yellow--;
        if(prevcol == 2) blue--;
        if(prevcol == 4) unknown--;

        int col = Robot.Wheel.getColor();
        colorAverage.set(loopcounter%accuracy,col);

        if(col == 0) red++;
        if(col == 3) green++;
        if(col == 1) yellow++;
        if(col == 2) blue++;
        if(col == 4) unknown++;

        max = Math.max(red, Math.max(green, Math.max(blue, Math.max(yellow, unknown))));

        loopcounter++;
        if(loopcounter == accuracy+2) loopcounter=0;

        return max;
    }

    public double calculateRevs(boolean direction) { //todo: update to be more accurate
        boolean hasred = false, hasgreen = false, hasblue = false, hasyellow = false;

        double col = calculateAverageColor();
        if(col == 0) hasred = true;
        if(col == 3) hasgreen = true;
        if(col == 2) hasblue = true;
        if(col == 1) hasyellow = true;

        if(hasred) revs+= direction ? 0.125 : -0.125;
        if(hasblue) revs+= direction ? 0.125 : -0.125;
        if(hasgreen) revs+= direction ? 0.125 : -0.125;
        if(hasyellow) revs+= direction ? 0.125 : -0.125;

        if(hasred && hasblue && hasgreen && hasyellow) {
            hasblue = false;
            hasred = false;
            hasgreen = false;
            hasyellow = false;
        }
        return revs;
    }

    public ArrayList<Integer> getSample() {
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
