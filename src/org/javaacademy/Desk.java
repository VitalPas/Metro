package org.javaacademy;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 Касса
 */
public class Desk {
    private final Map<LocalDate, BigInteger> mapIncome = new HashMap<>();

    public Map<LocalDate, BigInteger> getMapIncome() {
        return mapIncome;
    }

    public void salePriceTicket(LocalDate localDate, BigInteger income) {
        if (mapIncome.containsKey(localDate)) {
            mapIncome.put(localDate, mapIncome.get(localDate).add(income));
        } else {
            mapIncome.put(localDate, income);
        }
    }

    public void infoDesk() {
        if (mapIncome.isEmpty()) {
            return;
        }
        System.out.println(mapIncome);
    }

}
