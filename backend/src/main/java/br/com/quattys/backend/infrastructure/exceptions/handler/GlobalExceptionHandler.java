package br.com.quattys.backend.infrastructure.exceptions.handler;

import br.com.quattys.backend.infrastructure.exceptions.UserAlreadyExistsException;
import br.com.quattys.backend.infrastructure.exceptions.message.ErrorDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors,
                "Violação da integridade dos dados", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors,
                "Username or email is already being used", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName() + ": "+ error.getDefaultMessage());
        }
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors,
                "Verifique os dados enviados de acordo com formato exigido", HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }
}
