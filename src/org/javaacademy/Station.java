package org.javaacademy;

import java.time.Duration;
import java.util.List;

public class Station {
    private Metro metro;
    private Line line;
    private Station prevStation;
    private Station nextStation;
    private List<Station> transferStation;
    private Duration transferTime;
    private String name;


    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Station getPrevStation() {
        return prevStation;
    }

    public void setPrevStation(Station prevStation) {
        this.prevStation = prevStation;
    }

    public Station getNextStation() {
        return nextStation;
    }

    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
    }

    public List<Station> getTransferStation() {
        return transferStation;
    }

    public void setTransferStation(List<Station> transferStation) {
        this.transferStation = transferStation;
    }

    public Duration getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Duration transferTime) {
        this.transferTime = transferTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String printChangeLines() {
        if(transferStation == null) {
            return null;
        }
        return transferStation.get(0).getLine().getColorLine();
    }

    @Override
    public String toString() {
        return "Station{" +
                " name='" + name + '\'' +
                ", changeLines='" + printChangeLines() +
                '}';
    }
}
