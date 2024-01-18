package org.javaacademy;

import org.javaacademy.exception.LineCreationException;
import org.javaacademy.exception.StationCreationException;
import org.javaacademy.exception.TimeDurationException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Metro {
    private final String city = "Пермь";
    private List<Line> lines = new ArrayList<>();

    public String getName() {
        return city;
    }

    public void createLine(String color) throws LineCreationException {
       if(checkLineColor(color, lines)) {
           throw new LineCreationException("Линия с таким цветом уже существует");
       }
        lines.add(new Line(color));
    }

    public void createFirstStation(String color, String name, List<Station> stations) throws LineCreationException, StationCreationException {
        checkLine(color, name);
        Line line = lines.stream()
                .filter(line1 -> line1.getColorLine().equals(color))
                .findFirst().get();
        if(line.getStations().size() != 0) {
            throw new StationCreationException("На линии есть станции");
        }
        Station station = new Station();
        line.getStations().add(0,station);
        station.setMetro(Metro.this);
        station.setLine(line);
        station.setName(name);
    }

    public void createLastStation(String color, String name, Duration transferTime, List<Station> stations) throws LineCreationException, StationCreationException, TimeDurationException {
        checkLine(color, name);
        if(transferTime.isNegative() || transferTime.isZero()) {
            throw new TimeDurationException("Время перегона меньше или равно 0");
        }
        Line line = lines.stream()
                .filter(line1 -> line1.getColorLine().equals(color))
                .findFirst().get();
        Station station = new Station();
        station.setMetro(Metro.this);
        station.setLine(line);
        station.setName(name);
        line.getStations().add(station);
        int indexPrevStation = (line.getStations().indexOf(station) - 1);
        Station prevStation = line.getStations().get(indexPrevStation);
        checkPrevStation(prevStation);
        prevStation.setNextStation(station);
        station.setPrevStation(prevStation);
        prevStation.setTransferTime(transferTime);
        station.setTransferStation(stations);
    }

    private void checkLine(String color, String name) throws LineCreationException, StationCreationException {
        if(!checkLineColor(color, lines)) {
            throw new LineCreationException("Линии с таким цветом не существует");
        }
        if(checkStationName(name,lines)) {
            throw new StationCreationException("Такое имя есть на станциях");
        }
    }

    private void checkPrevStation(Station prevStation) throws StationCreationException {
        if(prevStation == null) {
            throw new StationCreationException("Предыдущая станция отсутсвует");
        }
        if(prevStation.getNextStation() != null) {
            throw new StationCreationException("Предыдущая станция имеет следющую станцию");
        }
    }

    private boolean checkStationName(String name, List<Line> lines) {
        return lines.stream().anyMatch(line -> line.getStations()
                        .stream()
                        .anyMatch(station -> station.getName().equals(name)));
    }

    private boolean checkLineColor(String color, List<Line> lines) {
        return lines.stream().anyMatch((line -> line.getColorLine().equals(color)));
    }

    public List<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "Metro{" +
                "city='" + city + '\'' +
                ", lines=" + lines +
                '}';
    }
}
