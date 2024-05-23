package br.com.quattys.backend.infrastructure.exceptions.message;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDetails(LocalDateTime timestamp,
                           List<String> errors, String message,
                           HttpStatus status) {
}
