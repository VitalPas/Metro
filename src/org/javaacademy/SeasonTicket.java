package org.javaacademy;

import java.time.LocalDate;

/**
Абонемент
 */
public class SeasonTicket {
    private final String number;
    private LocalDate dataStart;
    private LocalDate dateEnd;

    public SeasonTicket(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDate dataStart) {
        this.dataStart = dataStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "SeasonTicket{" +
                "number='" + number + '\'' +
                ", dataStart=" + dataStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
