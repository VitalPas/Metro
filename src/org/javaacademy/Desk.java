package org.javaacademy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Desk {
    private final Map<LocalDate, Integer> mapIncome;

    public Desk() {
        this.mapIncome = new HashMap<>();
    }

    public Map<LocalDate, Integer> getMapIncome() {
        return mapIncome;
    }

    public void savePriceTicket(LocalDate localDate, int income) {
        if (mapIncome.containsKey(localDate)) {
            mapIncome.put(localDate, mapIncome.get(localDate) + income);
        } else {
            mapIncome.put(localDate, income);
        }
    }

    public void infoDesk() {
        System.out.println(mapIncome);
    }

}
