package org.javaacademy;


import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Runner {
    public static void main(String[] args) {

        Metro metro = new Metro("Пермь");
        Line redLine = metro.createLine("Красная");
        Line blueLine = metro.createLine("Синяя");
        Set<Station> stationsRed = redLine.getStations();
        Set<Station> stationsBlue = blueLine.getStations();

        metro.createFirstStation("Красная", "Спортивная", null);
        metro.createLastStation("Красная", "Медведковская", Duration.ofMinutes(2).plusSeconds(21));
        metro.createLastStation("Красная", "Молодежная", Duration.ofMinutes(1).plusSeconds(58));
        metro.createLastStation("Красная", "Пермь 1", Duration.ofMinutes(3), stationsBlue);
        metro.createLastStation("Красная", "Пермь 2", Duration.ofMinutes(2).plusSeconds(10));
        metro.createLastStation("Красная", "Дворец Культуры", Duration.ofMinutes(4).plusSeconds(26));
        metro.createFirstStation("Синяя", "Пацанская", null);
        metro.createLastStation("Синяя", "Улица Кирова", Duration.ofMinutes(1).plusSeconds(30));
        metro.createLastStation("Синяя", "Тяжмаш", Duration.ofMinutes(1).plusSeconds(47), stationsRed);
        metro.createLastStation("Синяя", "Нижнекаменская", Duration.ofMinutes(3).plusSeconds(19));
        metro.createLastStation("Синяя", "Соборная", Duration.ofMinutes(1).plusSeconds(48));
        System.out.println(metro);

        Map<String, Station> redStation = stationsRed
                .stream()
                .collect(Collectors.toMap(Station::getName,
                        station -> station, (a, b) -> b, LinkedHashMap::new));
        Map<String, Station> blueStation = stationsBlue
                .stream()
                .collect(Collectors.toMap(Station::getName,
                        station -> station, (a, b) -> b, LinkedHashMap::new));


        redStation.get("Спортивная").saleTicket(LocalDate.of(2023, 1, 20), redStation.get("Спортивная"), redStation.get("Медведковская"));
        redStation.get("Спортивная").saleTicket(LocalDate.of(2023, 1, 20), redStation.get("Спортивная"), redStation.get("Медведковская"));
        blueStation.get("Пацанская").saleTicket(LocalDate.of(2023, 1, 20), redStation.get("Спортивная"), blueStation.get("Соборная"));
        redStation.get("Медведковская").saleTicket(LocalDate.of(2023, 1, 22), redStation.get("Спортивная"), blueStation.get("Соборная"));
        redStation.get("Медведковская").saleTicket(LocalDate.of(2023, 1, 22), redStation.get("Спортивная"), blueStation.get("Соборная"));
        redStation.get("Молодежная").saleTicket(LocalDate.of(2024, 1, 23), redStation.get("Спортивная"), blueStation.get("Соборная"));
        redStation.get("Молодежная").saleTicket(LocalDate.of(2023, 1, 23), redStation.get("Спортивная"), blueStation.get("Соборная"));


        redStation.get("Спортивная").saleSeasonTicket(LocalDate.of(2023, 1, 20));
        redStation.get("Спортивная").saleSeasonTicket(LocalDate.of(2023, 1, 20));
        redStation.get("Спортивная").saleSeasonTicket(LocalDate.of(2023, 1, 19));
        redStation.get("Спортивная").saleSeasonTicket(LocalDate.of(2023, 1, 20));
        redStation.get("Медведковская").saleSeasonTicket(LocalDate.of(2023, 1, 15));
        redStation.get("Пермь 1").saleSeasonTicket(LocalDate.of(2023, 1, 15));
        redStation.get("Пермь 1").saleSeasonTicket(LocalDate.of(2023, 1, 14));

        metro.infoFromAllDesk();


    }
}
