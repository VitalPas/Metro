package org.javaacademy;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.javaacademy.exception.*;

public class Metro {
    private static final String LINE_COLOR_EXISTS_EXCEPTION = "Линия с таким цветом уже существует";
    private static final String LINE_COLOR_NOT_EXISTS_EXCEPTION = "Линии с таким цветом не существует";
    private static final String STATION_NAME_EXISTS_EXCEPTION = "Такое имя есть на станциях";
    private static final String STATION_ON_LINE_EXISTS_EXCEPTION = "На линии есть станции";
    private static final String STATION_ON_LINE_NOT_EXISTS_EXCEPTION = "Станции не существует на линии";
    private static final String TRANSIT_TIME_EXCEPTION = "На линии есть станции";
    private static final String START_STATION_EQUALS_END_EXCEPTION = "Начальная станция равна конечной";
    private static final String PREV_STATION_NOT_EXISTS_EXCEPTION = "Предыдущая станция отсутсвует";
    private static final String PREV_STATION_HAS_NEXT_EXCEPTION = "Предыдущая станция имеет следющую станцию";

    private final String city;
    private final HashSet<Line> lines = new HashSet<>();

    public Metro(String city) {
        this.city = city;
    }

    public Set<Line> getLines() {
        return lines;
    }

    public Line createLine(String color) throws LineCreationException {
        if (isLineWithColorExists(color, lines)) {
            throw new LineCreationException(LINE_COLOR_EXISTS_EXCEPTION);
        }
        Line line = new Line(color);
        line.setMetro(this);
        lines.add(line);
        return line;
    }

    public void createFirstStation(String color, String name, List<Station> stations)
            throws LineCreationException, StationCreationException {
        if (!isLineWithColorExists(color, lines)) {
            throw new LineCreationException(LINE_COLOR_NOT_EXISTS_EXCEPTION);
        }
        if (checkStationName(name, lines)) {
            throw new StationCreationException(STATION_NAME_EXISTS_EXCEPTION);
        }
        Line line = getLine(color);
        if (line.getStations().size() != 0) {
            throw new StationCreationException(STATION_ON_LINE_EXISTS_EXCEPTION);
        }
        line.addFirstStation(name);
    }

    public void createLastStation(String color, String name, Duration transferTime,
                                  Set<Station> stations)
            throws LineCreationException, StationCreationException, TimeDurationException {
        if (!isLineWithColorExists(color, lines)) {
            throw new LineCreationException(LINE_COLOR_NOT_EXISTS_EXCEPTION);
        }
        if (checkStationName(name, lines)) {
            throw new StationCreationException(STATION_NAME_EXISTS_EXCEPTION);
        }
        if (transferTime.isNegative() || transferTime.isZero()) {
            throw new TimeDurationException(TRANSIT_TIME_EXCEPTION);
        }
        Line line = getLine(color);
        Station station = line.addLastStation(name);
        int indexPrevStation = line.getStations().size() - 1;
        Station prevStation = line.getStations()
                .stream()
                .skip(indexPrevStation - 1)
                .findFirst().get();
        checkPrevStation(prevStation);
        prevStation.setNextStation(station);
        station.setPrevStation(prevStation);
        prevStation.setTransferTime(transferTime);
        station.addTransferStation(stations);
    }

    public int countTransferBetweenStation(Station stationStart, Station stationEnd)
            throws TransferException, StationExistException {
        checkEqualsStation(stationStart, stationEnd);
        isStationExist(stationStart, stationEnd);
        int count = 0;
        if (stationStart.getLine().getColorLine().equals(stationEnd.getLine().getColorLine())) {
            count = countTransferBetweenStationOnLine(stationStart, stationEnd);
        } else {
            Station stationTransfer = findStationForTransfer(stationStart.getLine(),
                    stationEnd.getLine());
            count += countTransferBetweenStationOnLine(stationStart, stationTransfer);
            stationTransfer = findStationForTransfer(stationEnd.getLine(), stationStart.getLine());
            count += countTransferBetweenStationOnLine(stationTransfer, stationEnd);
        }
        return count;
    }

    private void isStationExist(Station station1, Station station2) throws StationExistException {
        if (lines.stream().noneMatch(line -> line.getStations().contains(station1))
            || lines.stream().noneMatch(line -> line.getStations().contains(station2))) {
            throw new StationExistException(STATION_ON_LINE_NOT_EXISTS_EXCEPTION);
        }
    }

    private void checkEqualsStation(Station station1, Station station2) throws TransferException {
        if (station1.equals(station2)) {
            throw new TransferException(START_STATION_EQUALS_END_EXCEPTION);
        }
    }

    private void checkPrevStation(Station prevStation) throws StationCreationException {
        if (prevStation == null) {
            throw new StationCreationException(PREV_STATION_NOT_EXISTS_EXCEPTION);
        }
        if (prevStation.getNextStation() != null) {
            throw new StationCreationException(PREV_STATION_HAS_NEXT_EXCEPTION);
        }
    }

    private boolean checkStationName(String name, HashSet<Line> lines) {
        return lines.stream().anyMatch(line -> line.getStations()
                        .stream()
                        .anyMatch(station -> station.getName().equals(name)));
    }

    private boolean isLineWithColorExists(String color, HashSet<Line> lines) {
        return lines.stream().anyMatch((line -> line.getColorLine().equals(color)));
    }

    private Line getLine(String color) {
        return lines.stream()
                .filter(line -> line.getColorLine().equals(color))
                .findFirst().get();
    }

    private Station findStationForTransfer(Line line1, Line line2) {
        if (line1.getColorLine().equals(line2.getColorLine())) {
            return null;
        }
        return line1.getStations().stream()
                .filter(station -> station.getTransferStation() != null)
                .findFirst()
                .get();
    }

    private int countTransferBetweenStationOnLine(Station stationStart, Station stationEnd)
            throws TransferException {
        int count = countTransferBetweenNextStation(stationStart, stationEnd);
        if (count != -1) {
            return count;
        } else {
            count = countTransferBetweenPrevStation(stationStart, stationEnd);
        }
        if (count == -1) {
            throw new TransferException("Нет пути из станции " + stationStart.getName()
                    + " до " + stationEnd.getName());
        }
        return count;
    }

    private int countTransferBetweenNextStation(Station stationStart, Station stationEnd) {
        int count = 0;
        if (stationStart.getNextStation() == null) {
            return -1;
        }
        if (stationStart.getNextStation().equals(stationEnd)) {
            return 1;
        }
        while (!stationStart.equals(stationEnd)) {
            count++;
            stationStart = stationStart.getNextStation();
            if (stationStart == null) {
                return -1;
            }
        }
        return count;
    }

    private int countTransferBetweenPrevStation(Station stationStart, Station stationEnd) {
        int count = 0;
        if (stationStart.getPrevStation().equals(stationEnd)) {
            return 1;
        }
        while (!stationStart.equals(stationEnd)) {
            count++;
            stationStart = stationStart.getPrevStation();
            if (stationStart == null) {
                return -1;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Metro{" +
                "city='" + city + '\'' +
                ", lines=" + lines +
                '}';
    }
}
