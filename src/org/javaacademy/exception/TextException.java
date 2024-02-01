package org.javaacademy.exception;

public enum TextException {
    LINE_COLOR_EXISTS_EXCEPTION("Линия с таким цветом уже существует"),
    LINE_COLOR_NOT_EXISTS_EXCEPTION("Линии с таким цветом не существует"),
    STATION_NAME_EXISTS_EXCEPTION("Такое имя есть на станциях"),
    STATION_ON_LINE_EXISTS_EXCEPTION("На линии есть станции"),
    STATION_ON_LINE_NOT_EXISTS_EXCEPTION("Станции не существует на линии"),
    TRANSIT_TIME_EXCEPTION("На линии есть станции"),
    START_STATION_EQUALS_END_EXCEPTION("Начальная станция равна конечной"),
    PREV_STATION_NOT_EXISTS_EXCEPTION("Предыдущая станция отсутсвует"),
    PREV_STATION_HAS_NEXT_EXCEPTION("Предыдущая станция имеет следющую станцию"),
    ;
    private final String text;

    TextException(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
