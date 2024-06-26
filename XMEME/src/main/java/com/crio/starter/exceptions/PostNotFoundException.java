package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ApplicationError {

    private static final String DEFAULT_MSG = "The post you searched for is not found";


    public PostNotFoundException() {
        super(DEFAULT_MSG, HttpStatus.NOT_FOUND);
    }

    public PostNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }


}
