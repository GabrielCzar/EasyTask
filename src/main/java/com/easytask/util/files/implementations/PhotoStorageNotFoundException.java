package com.easytask.util.files.implementations;

public class PhotoStorageNotFoundException extends PhotoStorageException {

    public PhotoStorageNotFoundException(String message) {
        super(message);
    }

    public PhotoStorageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}