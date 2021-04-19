package com.basmaonline.com.basmaonline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotAddedException extends RuntimeException{

    public CategoryNotAddedException(String message) {
        super(message);
    }

}
