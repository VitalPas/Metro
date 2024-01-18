package org.javaacademy;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String colorLine;
    private List<Station> stations = new ArrayList<>();
    private Metro metro;

    public Line(String colorLine) {
        this.colorLine = colorLine;
    }

    public String getColorLine() {
        return colorLine;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setColorLine(String colorLine) {
        this.colorLine = colorLine;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    @Override
    public String toString() {
        return "Line{" +
                "colorLine='" + colorLine + '\'' +
                ", stations=" + stations +
                '}';
    }
}
