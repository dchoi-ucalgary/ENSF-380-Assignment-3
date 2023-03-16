package edu.ucalgary.oop;

import java.util.*;

public class RobotDataRecord implements Cloneable{
    private ArrayList<RobotDataLine> log = new ArrayList <>();

    public RobotDataRecord(String[] array) { 
        for (int i = 0; i < array.length; i++) {
            try {
                this.log.add(new RobotDataLine(array[i]));
            }
            catch (IllegalArgumentException e) {
                // ??? = stonks
            }
        }
    }

    public RobotDataLine getLine(int index) {
        return this.log.get(index);
    }

    public ArrayList<RobotDataLine> getDataRecord() {
        return this.log;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RobotDataRecord cloned = (RobotDataRecord) super.clone();
        cloned.log = new ArrayList<>();

        for (RobotDataLine line : this.log) {
            cloned.log.add((RobotDataLine) line.clone());
        }

    return cloned;
}
    
}