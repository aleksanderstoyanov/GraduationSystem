package com.graduation.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ThesisNotFoundException extends RuntimeException{
    public ThesisNotFoundException(String message) {
        super(message);
    }
}
