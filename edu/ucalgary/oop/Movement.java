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
            this.direction = movement.substring(movement.lastIndexOf(' '), movement.length());
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
        if(this.direction == "NE") {
            return (Directions.NE.toString());
        }
        else if(this.direction == "NW") {
            return (Directions.NW.toString());
        }
        else if(this.direction == "SE") {
            return (Directions.SE.toString());
        }
        else if(this.direction == "SW") {
            return (Directions.SW.toString());
        }
        else if(this.direction == "E") {
            return (Directions.E.toString());
        }
        else if(this.direction == "W") {
            return (Directions.W.toString());
        }
        else {
            return "";
        }
    }
}
