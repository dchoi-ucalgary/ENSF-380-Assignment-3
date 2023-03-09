package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sensor implements Cloneable {
    private final String sensor;
    private static final String REGEX = "\\(([a-z]+)\\)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Sensor(String sensor) throws IllegalArgumentException {
        if (sensor == null) {
            throw new IllegalArgumentException("Sensor cannot be null");
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

    public static Sensor valueOf(String sensorString) throws IllegalArgumentException {
        Matcher matcher = PATTERN.matcher(sensorString);
        if (matcher.find()) {
            return new Sensor(matcher.group(1));
        }
        throw new IllegalArgumentException("Invalid sensor string: " + sensorString);
    }
}
