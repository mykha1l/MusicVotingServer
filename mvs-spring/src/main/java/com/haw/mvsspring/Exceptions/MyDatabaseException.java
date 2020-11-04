package com.haw.mvsspring.Exceptions;

public class MyDatabaseException extends RuntimeException {

    public MyDatabaseException() {
        super();
    }

    public MyDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDatabaseException(String message) {
        super(message);
    }

    public MyDatabaseException(Throwable cause) {
        super(cause);
    }

}
