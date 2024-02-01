package org.javaacademy.exception;

public class TimeDurationException extends RuntimeException {
    public TimeDurationException(TextException message) {
        super(message.getText());
    }
}
