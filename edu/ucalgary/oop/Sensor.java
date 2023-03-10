package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sensor implements Cloneable, FormattedOutput{

    private String sensor;
    private String REGEX = "([a-z]+)";

    private Pattern PATTERN = Pattern.compile(REGEX);

    public Sensor(String sensor) throws IllegalArgumentException{
        Matcher matches = PATTERN.matcher(sensor);
        if(!matches.matches()) {
            throw new IllegalArgumentException("Not a valid argument entry");
        }
        this.sensor = sensor;

    }

    public String getSensor() {
        return this.sensor;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String getFormatted(){
        return "Sensor: " + this.sensor;
    }
}
