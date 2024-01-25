package org.javaacademy;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import org.javaacademy.exception.StationExistException;
import org.javaacademy.exception.TransferException;

public class Station {
    private final Metro metro;
    private final Line line;
    private Station prevStation;
    private Station nextStation;
    private Set<Station> transferStation;
    private Duration transferTime;
    private String name;
    private final Desk desk;

    public Station(Metro metro, Line line, String name) {
        this.metro = metro;
        this.line = line;
        this.name = name;
        this.desk = new Desk();
    }

    public Desk getDesk() {
        return desk;
    }

    public Line getLine() {
        return line;
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

    public Set<Station> getTransferStation() {
        return transferStation;
    }

    public void setTransferStation(Set<Station> transferStation) {
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

    public void addTransferStation(Set<Station> transferStations) {
        if (transferStations == null) {
            return;
        }
        this.transferStation = transferStations;
    }

    private String printChangeLines() {
        if (transferStation == null) {
            return null;
        }
        return transferStation.stream()
                .findFirst()
                .get()
                .getLine()
                .getColorLine();
    }

    public void saleTicket(LocalDate localDate, Station stationStart, Station stationEnd)
            throws StationExistException, TransferException {
        int ticketPrice = metro.countTransferBetweenStation(stationStart, stationEnd) * 5 + 20;
        desk.savePriceTicket(localDate, ticketPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Station{" +
                " name='" + name + '\'' +
                ", changeLines='" + printChangeLines() +
                '}';
    }


}
