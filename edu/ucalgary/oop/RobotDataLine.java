package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RobotDataLine implements Cloneable {
    private static final String DATE_REGEX = "\\[([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})\\]";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private static final String ROBOT_REGEX = "\\s([0-9]{3}[A-Z]{1})\\s";
    private static final Pattern ROBOT_PATTERN = Pattern.compile(ROBOT_REGEX);

    private String dataLine;
    private String robotID;
    private Sensor sensor;
    private Movement movement;
    private LocalDate date;

    public RobotDataLine(String line) {
        this.dataLine = line;
    
        String date = line.substring(line.indexOf('['), line.indexOf(']') + 1);
        Matcher date_matches = DATE_PATTERN.matcher(date);

        String robotID = line.substring(5, 11);
        Matcher robot_matches = ROBOT_PATTERN.matcher(robotID);
        
        if(!date_matches.matches()) {
            throw new IllegalArgumentException("Not a valid date input");
        }

        if(!robot_matches.matches()) {
            throw new IllegalArgumentException("Not a valid robot input");
        }

        int moveStart = line.indexOf('"');
        int moveEnd   = line.lastIndexOf(' ');

        int sensorStart = line.indexOf("(") + 1;
        int sensorEnd = line.lastIndexOf('"') - 1;

        this.movement = new Movement(line.substring(moveStart, moveEnd));
        this.sensor = new Sensor(line.substring(sensorStart, sensorEnd));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String test_date = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        this.date = LocalDate.parse(test_date, formatter);
        this.robotID = robotID;

    }

    public String getRobotID(){ return robotID.trim(); }
    public String getDataLine(){ return dataLine; }
    public Sensor getSensor(){ return sensor; }
    public Movement getMovement(){ return movement; }
    public LocalDate getDate() { return date; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RobotDataLine cloned = (RobotDataLine) super.clone();
        cloned.sensor = (Sensor) this.sensor.clone();
        cloned.movement = (Movement) this.movement.clone();
        cloned.date = LocalDate.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth());
        return cloned;
    }
}