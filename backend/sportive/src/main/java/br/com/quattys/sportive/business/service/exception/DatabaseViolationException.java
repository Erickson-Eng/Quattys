package br.com.quattys.sportive.business.service.exception;



public class DatabaseViolationException extends RuntimeException{
    public DatabaseViolationException(String databaseError) {
    }
}
