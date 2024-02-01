package org.javaacademy.exception;

public class StationCreationException extends RuntimeException {
    public StationCreationException(TextException message) {
        super(message.getText());
    }
}
