package com.haw.mvsspring.Exceptions;

public class ErrorResponseBody {

    private String error;

    public ErrorResponseBody(final String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public void setError(final String error) {
        this.error = error;
    }

}
