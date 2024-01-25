package org.javaacademy;


import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import org.javaacademy.exception.*;


public class Runner {
    public static void main(String[] args) throws LineCreationException, StationCreationException, TimeDurationException, StationExistException, TransferException {

        Metro metro = new Metro("Пермь");
        Line redLine = metro.createLine("Красная");
        Line blueLine = metro.createLine("Синяя");
        Set<Station> stationsRed = redLine.getStations();
        Set<Station> stationsBlue = blueLine.getStations();

        metro.createFirstStation("Красная", "Спортивная", null);
        metro.createLastStation("Красная", "Медведковская", Duration.ofMinutes(2).plusSeconds(21), null);
        metro.createLastStation("Красная", "Молодежная", Duration.ofMinutes(1).plusSeconds(58), null);
        metro.createLastStation("Красная", "Пермь 1", Duration.ofMinutes(3), stationsBlue);
        metro.createLastStation("Красная", "Пермь 2", Duration.ofMinutes(2).plusSeconds(10), null);
        metro.createLastStation("Красная", "Дворец Культуры", Duration.ofMinutes(4).plusSeconds(26), null);
        metro.createFirstStation("Синяя", "Пацанская", null);
        metro.createLastStation("Синяя", "Улица Кирова", Duration.ofMinutes(1).plusSeconds(30), null);
        metro.createLastStation("Синяя", "Тяжмаш", Duration.ofMinutes(1).plusSeconds(47), stationsRed);
        metro.createLastStation("Синяя", "Нижнекаменская", Duration.ofMinutes(3).plusSeconds(19), null);
        metro.createLastStation("Синяя", "Соборная", Duration.ofMinutes(1).plusSeconds(48), null);
        //System.out.println(metro);

        List<Station> stationList = new LinkedList<>(stationsRed);
        List<Station> stationList2 = new LinkedList<>(stationsBlue);
        Station station1 = stationList.get(0);
        Station station2 = stationList.get(1);
        LinkedHashSet<Station> stationsRedLine = redLine.getStations();
        LinkedHashSet<Station> stationsBlueLine = blueLine.getStations();
        stationsRedLine.stream().forEach(station -> System.out.println(station));
        System.out.println("___________________________________________");
        stationsBlueLine.stream().forEach(station -> System.out.println(station));
        System.out.println("___________________________________________");
        System.out.println(station1);
        System.out.println(station2);

        try {
            int countTransferBetweenStation = metro.countTransferBetweenStation(station1, station2);
            System.out.println("Количетсво переходов от " + station1.getName() + " до "
                    + station2.getName() + " = "  + countTransferBetweenStation);
        } catch (TransferException | StationExistException e) {
            System.out.println(e.getMessage());
        }

        station1.saleTicket(LocalDate.of(2023, 1, 20), station1, station2);
        station1.saleTicket(LocalDate.of(2023, 1, 20), station1, station2);
        station1.getDesk().infoDesk();


    }
}
