package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDatFoundException(NoDataFoundException ex, WebRequest request){

        Map<String,Object> error=new LinkedHashMap<>();
        error.put("Fecha", LocalDate.now());
        error.put("Message", ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
