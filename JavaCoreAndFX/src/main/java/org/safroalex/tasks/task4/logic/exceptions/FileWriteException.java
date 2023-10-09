package org.safroalex.tasks.task4.logic.exceptions;

import java.io.IOException;

public class FileWriteException extends IOException {
    public FileWriteException(String message) {
        super(message);
    }
}
