package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RobotDataLine implements Cloneable {
    private final String dataLine;
    private final String robotID;
    private final Sensor sensor;
    private final Movement movement;
    private final LocalDate date;

    private static final String DATE_REGEX = "\\[([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})\\]";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private static final String ROBOT_REGEX = "\\s([0-9]{3}[A-Z]{1})\\s";
    private static final Pattern ROBOT_PATTERN = Pattern.compile(ROBOT_REGEX);

    public RobotDataLine(String line) throws IllegalArgumentException {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        this.dataLine = line;
        this.robotID = extractRobotID(line);
        this.sensor = extractSensor(line);
        this.movement = extractMovement(line);
        this.date = extractDate(line);
    }

    private String extractRobotID(String line) throws IllegalArgumentException {
        Matcher matcher = ROBOT_PATTERN.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Could not extract robot ID from line: " + line);
    }

    private Sensor extractSensor(String line) throws IllegalArgumentException {
        int startIndex = line.indexOf("[") + 1;
        int endIndex = line.indexOf("]");
        String sensorString = line.substring(startIndex, endIndex);
        try {
            return Sensor.valueOf(sensorString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sensor value: " + sensorString);
        }
    }

    private Movement extractMovement(String line) throws IllegalArgumentException {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid number of tokens in line: " + line);
        }
        try {
            return Movement.valueOf(tokens[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid movement value: " + tokens[2]);
        }
    }

    private LocalDate extractDate(String line) throws IllegalArgumentException {
        Matcher matcher = DATE_PATTERN.matcher(line);
        if (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            return LocalDate.of(year, month, day);
        }
        throw new IllegalArgumentException("Could not extract date from line: " + line);
    }

    public String getRobotID() {
        return this.robotID;
    }

    public String getDataLine() {
        return this.dataLine;
    }

    public Sensor getSensor() {
        return this.sensor;
    }

    public Movement getMovement() {
        return this.movement;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since Cloneable is implemented
            throw new InternalError(e);
        }
    }
}