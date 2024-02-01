package org.javaacademy.exception;

public class StationExistException extends RuntimeException {
    public StationExistException(TextException message) {
        super(message.getText());
    }
}
