package org.javaacademy;

import java.util.LinkedHashSet;

/**
 Линия метро
 */
public class Line {
    private String colorLine;
    private final LinkedHashSet<Station> stations = new LinkedHashSet<>();
    private Metro metro;

    public Line(String colorLine) {
        this.colorLine = colorLine;
    }

    public String getColorLine() {
        return colorLine;
    }

    public LinkedHashSet<Station> getStations() {
        return stations;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setColorLine(String colorLine) {
        this.colorLine = colorLine;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    public void addFirstStation(String name) {
        Station station = new Station(metro, this, name);
        stations.add(station);
    }

    public Station addLastStation(String name) {
        Station station = new Station(metro, this, name);
        stations.add(station);
        return station;
    }

    @Override
    public String toString() {
        return "Line{" +
                "colorLine='" + colorLine + '\'' +
                ", stations=" + stations +
                '}';
    }
}
