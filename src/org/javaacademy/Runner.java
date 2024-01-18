package org.javaacademy;


import org.javaacademy.exception.LineCreationException;
import org.javaacademy.exception.StationCreationException;
import org.javaacademy.exception.TimeDurationException;

import java.time.Duration;
import java.util.List;


public class Runner {
    public static void main(String[] args) throws LineCreationException, StationCreationException, TimeDurationException {

        Metro metro = new Metro();
        metro.createLine("Красная");
        metro.createLine("Синяя");
        List<Line> lines = metro.getLines();
        List<Station> stationsRed = null;
        List<Station> stationsBlue = null;
        for (Line line : lines) {
            if(line.getColorLine().equals("Красная")) {
                stationsRed = line.getStations();
            }
            if(line.getColorLine().equals("Синяя")) {
                stationsBlue = line.getStations();
            }
        }

        metro.createFirstStation("Красная", "Спортивная", null);
        metro.createLastStation("Красная", "Медведковская", Duration.ofMinutes(2).plusSeconds(21), null);
        metro.createLastStation("Красная", "Молодежная", Duration.ofMinutes(1).plusSeconds(58), null);
        metro.createLastStation("Красная", "Пермь 1", Duration.ofMinutes(3), stationsBlue);
        metro.createLastStation("Красная", "Пермь 2", Duration.ofMinutes(2).plusSeconds(10), null);
        metro.createLastStation("Красная", "Дворец Культуры", Duration.ofMinutes(4).plusSeconds(26), null);
        metro.createFirstStation("Синяя", "Пацанская", null);
        metro.createLastStation("Красная", "Улица Кирова", Duration.ofMinutes(1).plusSeconds(30), null);
        metro.createLastStation("Красная", "Тяжмаш", Duration.ofMinutes(1).plusSeconds(47), stationsRed);
        metro.createLastStation("Красная", "Нижнекаменская", Duration.ofMinutes(3).plusSeconds(19), null);
        metro.createLastStation("Красная", "Соборная", Duration.ofMinutes(1).plusSeconds(48), null);

        System.out.println(metro);
    }
}
