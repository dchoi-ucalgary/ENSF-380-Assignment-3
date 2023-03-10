package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movement implements Cloneable, FormattedOutput{
    private static final String REGEX = "\"([A-Z]+) - ([A-Z]{1,2})";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private String action;
    private String direction;

    public Movement(String movement) throws IllegalArgumentException {
        Matcher matcher = PATTERN.matcher(movement);
        if(matcher.matches()){
            this.action = movement.substring(movement.indexOf('"') + 1, movement.indexOf(' '));
            this.direction = movement.substring(movement.lastIndexOf(' ') + 1, movement.length());
        }
        else {
            throw new IllegalArgumentException("Invalid movement string: " + movement);
        }
    }

    public String getAction(){
        return this.action;
    }

    public String getDirection(){
        return this.direction;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Movement cloned = (Movement) super.clone();
        cloned.action = this.action;
        cloned.direction = this.direction;
        return cloned;
    }

    @Override
    public String getFormatted(){
        if("NE".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.NE.toString();
        }
        if("NW".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.NW.toString();
        }
        if("SE".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.SE.toString();
        }
        if("SW".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.SW.toString();
        }
        if("N".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.N.toString();
        }
        if("E".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.E.toString();
        }
        if("S".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.S.toString();
        }
        if("W".equals(this.direction)) {
            return "Action: " + this.action + ", " + "Direction: " + Directions.W.toString();
        }
        else{
            return "";
        }
    }
}
