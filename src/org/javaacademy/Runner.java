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
        System.out.println(metro);

        List<Station> redStations = stationsRed.stream().toList();
        List<Station> blueStations = stationsBlue.stream().toList();

        redStations.get(0).saleTicket(LocalDate.of(2023, 1, 20), redStations.get(0), redStations.get(1));
        redStations.get(0).saleTicket(LocalDate.of(2023, 1, 21), redStations.get(0), blueStations.get(4));
        redStations.get(1).saleTicket(LocalDate.of(2023, 1, 22), redStations.get(0), blueStations.get(4));
        redStations.get(2).saleTicket(LocalDate.of(2023, 1, 23), redStations.get(0), blueStations.get(4));

        redStations.get(0).getDesk().infoDesk();
        redStations.get(1).getDesk().infoDesk();
        redStations.get(2).getDesk().infoDesk();


    }
}
