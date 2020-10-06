package com.haw.mvsspring.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyDatabaseExeption extends RuntimeException {

    public MyDatabaseExeption() {
        super();
    }

    public MyDatabaseExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDatabaseExeption(String message) {
        super(message);
    }

    public MyDatabaseExeption(Throwable cause) {
        super(cause);
    }

}
