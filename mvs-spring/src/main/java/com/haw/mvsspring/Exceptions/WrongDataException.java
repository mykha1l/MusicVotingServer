package com.haw.mvsspring.Exceptions;

public class WrongDataException extends RuntimeException {

    public WrongDataException() {
        super();
    }

    public WrongDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDataException(String message) {
        super(message);
    }

    public WrongDataException(Throwable cause) {
        super(cause);
    }

}
