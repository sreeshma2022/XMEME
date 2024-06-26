package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationError extends Exception {

    private static final String DEFAULT_MSG = "Server side error. Please try again.";

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    public ApplicationError() {
        super(DEFAULT_MSG);
    }

    public ApplicationError(String msg) {
        super(msg);
    }

    public ApplicationError(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }


}
