package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;

public class PostAlreadyExistException extends ApplicationError {
    private static final String DEFAULT_MSG = "The post you are trying to post already exist";


    public PostAlreadyExistException() {
        super(DEFAULT_MSG, HttpStatus.CONFLICT);
    }

    public PostAlreadyExistException(String msg) {
        super(msg, HttpStatus.CONFLICT);
    }
}
