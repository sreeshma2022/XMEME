package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPostException extends ApplicationError {

    private static final String DEFAULT_MSG = "The post you are trying to create is not valid.";

    public InvalidPostException() {
        super(DEFAULT_MSG, HttpStatus.BAD_REQUEST);
    }

    public InvalidPostException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
