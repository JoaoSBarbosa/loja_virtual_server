package com.barbosacode.lojavirtual.exceptions.handler;

import com.barbosacode.lojavirtual.exceptions.ControllerNotFoundException;
import com.barbosacode.lojavirtual.exceptions.components.StandardError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

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


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        String message = "";
        if(ex instanceof MethodArgumentNotValidException){
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for(ObjectError objectError : list){
                message += objectError.getDefaultMessage() + "\n";
            }
        }else {
            message += ex.getMessage();
        }
        standardError.setMessage(message);
        standardError.setStatus(status.value());

        return new ResponseEntity<>(standardError, headers, status);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
    protected ResponseEntity<StandardError> handleConstraintViolation(Exception ex, WebRequest request) {
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        String message = "";

        if(ex instanceof ConstraintViolationException){
            message = "Erro de chave estrageira: " +((ConstraintViolationException) ex).getConstraintViolations().iterator().next().getMessage();
        }else if(ex instanceof SQLException){
            message = "Erro no Banco de Dados: "+ ((SQLException) ex).getCause().getMessage();
        }else if(ex instanceof DataIntegrityViolationException){
            message = "Erro de integridade de dados: "+ ((DataIntegrityViolationException) ex).getCause().getMessage();
        }else {
            message += ex.getMessage();
        }

        standardError.setMessage(message);
        standardError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(standardError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
