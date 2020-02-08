package frc.robot.wheel;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.auto.auto;
import frc.robot.main.RobotMap;

import java.util.ArrayList;

import static frc.robot.main.Robot.moveFoward;

public class ColorMatcherCommand extends Command {

    private final int ARRAY_SIZE = 19;

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    public static ColorSensorV3.RawColor color;
    public static String colorString;
    public ArrayList <String> colorAverage = new ArrayList<>();
    public int loopCounter = 0;
    public int redCount = 0;
    public int blueCount = 0;
    public int greenCount = 0;
    public int yellowCount = 0;
    public int unknownCount = 0;
    public int largestColor;


    public ColorMatcherCommand() {

    }

    @Override
    protected void initialize() {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
        colorString = "Unknown";
        for(int i = 0; i < ARRAY_SIZE+1; i++)colorAverage.add("Unknown");



    }

    @Override
    protected void execute() {
        Color detectedColor = RobotMap.colorSensor.getColor();

        /**
         * Run the color match algorithm on our detected color
         */

        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";

        } else if (match.color == kRedTarget) {
            colorString = "Red";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }

        String previouscolor = colorAverage.get(loopCounter%ARRAY_SIZE);

        colorAverage.set(loopCounter%ARRAY_SIZE, colorString);

        if(loopCounter == 300000000)loopCounter = 0;

        if(previouscolor.equals("Blue")) blueCount--;
        if(previouscolor.equals("Red")) redCount--;
        if(previouscolor.equals("Green")) greenCount--;
        if(previouscolor.equals("Yellow")) yellowCount--;
        if(previouscolor.equals("Unknown")) unknownCount--;

        if(colorAverage.get(loopCounter % ARRAY_SIZE).equals("Blue")) blueCount++;
        if(colorAverage.get(loopCounter % ARRAY_SIZE).equals("Red")) redCount++;
        if(colorAverage.get(loopCounter % ARRAY_SIZE).equals("Yellow")) yellowCount++;
        if(colorAverage.get(loopCounter % ARRAY_SIZE).equals("Green")) greenCount++;
        if(colorAverage.get(loopCounter % ARRAY_SIZE).equals("Unknown")) unknownCount++;

        /*for(int i = 0; i <= 30; i++){
            if(colorAverage.get(i) == "Blue") blueCount++;
            if(colorAverage.get(i) == "Red") redCount++;
            if(colorAverage.get(i) == "Yellow") yellowCount++;
            if(colorAverage.get(i) == "Green") greenCount++;
            if(colorAverage.get(i) == "Unknown") unknownCount++;

        }*/

        largestColor = Math.max(redCount, Math.max(greenCount, Math.max(blueCount, Math.max(yellowCount, unknownCount))));
        if(largestColor == blueCount)System.out.println("Blue");    //TODO FIND MOTOR FUNCTIONS
        if(largestColor == greenCount)System.out.println("Green");
        if(largestColor == yellowCount)System.out.println("Yellow");
        if(largestColor == redCount)System.out.println("Red");
        if(largestColor == unknownCount)System.out.println("Unknown");

        loopCounter++;
        //TODO maybe add the if two are equal one to one of them to fix overlay issue.

       // System.out.println(colorAverage);



       //System.out.println(colorString);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
