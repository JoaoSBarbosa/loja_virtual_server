package com.barbosacode.lojavirtual.exceptions.handler;

import com.barbosacode.lojavirtual.exceptions.ControllerNotFoundException;
import com.barbosacode.lojavirtual.exceptions.components.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> controllerNotFound(ControllerNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setMessage(e.getMessage());
        error.setStatus(status.value());
        error.setPath(request.getRequestURI());
        error.setError("Recurso não encontrado");
        error.setTimestamp(Instant.now());
        return new ResponseEntity<>(error, status);
    }
}
