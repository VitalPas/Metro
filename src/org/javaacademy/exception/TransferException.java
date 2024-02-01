package org.javaacademy.exception;

public class TransferException extends RuntimeException {
    public TransferException(TextException message) {
        super(message.getText());
    }

    public TransferException(String message) {
        super(message);
    }
}
