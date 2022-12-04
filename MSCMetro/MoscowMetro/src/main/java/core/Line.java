package main.java.core;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>{
    private final String number;
    private final List<Station> stations;

    public Line(String number){
        this.number = number;
        stations = new ArrayList<>();
    }

    public String getNumber(){
        return number;
    }

    public void addStation(Station station){
        stations.add(station);
    }

    public List<Station> getStations(){
        return stations;
    }

    @Override
    public int compareTo(Line line){
        return number.compareToIgnoreCase(line.getNumber());
    }

    @Override
    public boolean equals(Object obj){
        return compareTo((Line) obj) == 0;
    }
}
