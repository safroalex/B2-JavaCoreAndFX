package org.safroalex.tasks.task4.logic.exceptions;

import java.io.IOException;

public class InvalidFileFormatException extends IOException {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
