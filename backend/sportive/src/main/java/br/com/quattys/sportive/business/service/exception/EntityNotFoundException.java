package br.com.quattys.sportive.business.service.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String addressNotRegistered, HttpStatus httpStatus) {

    }
}
