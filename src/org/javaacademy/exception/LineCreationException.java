package org.javaacademy.exception;

public class LineCreationException extends RuntimeException {
    public LineCreationException(TextException message) {
        super(message.getText());
    }
}
