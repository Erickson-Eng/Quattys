package br.com.quattys.sportive.business.service.exception;

import org.springframework.http.HttpStatus;

public class DatabaseViolationException extends RuntimeException{
    public DatabaseViolationException(String databaseError, HttpStatus error) {
    }
}
