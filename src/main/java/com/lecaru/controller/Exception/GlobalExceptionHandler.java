package com.lecaru.controller.Exception;

import com.lecaru.infra.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoContentException(NotFoundException ex) {
        return new ResponseEntity<>(ErrorMessage.send(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleUnexpectedException(Throwable unexpectedException) {
        String message = "Ocorreu um erro inesperado do sistema.";
        LOGGER.error(message, unexpectedException);
        return new ResponseEntity<>(ErrorMessage.send(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
