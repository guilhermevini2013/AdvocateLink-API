package com.example.AdvocateLink.controllers.exceptions;

import com.example.AdvocateLink.services.EmployeeService;
import com.example.AdvocateLink.services.exceptions.DataBaseException;
import com.example.AdvocateLink.services.exceptions.LackOfInformationException;
import com.example.AdvocateLink.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExHandler {
    final private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private HttpStatus status;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        status = HttpStatus.NOT_FOUND;
        logger.error(e.getMessage());
        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), request.getServletPath()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> DataBaseError(DataBaseException e, HttpServletRequest request) {
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.error(e.getMessage());
        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), request.getServletPath()));
    }

    @ExceptionHandler(LackOfInformationException.class)
    public ResponseEntity<StandardError> lackInformation(LackOfInformationException e, HttpServletRequest request) {
        status = HttpStatus.BAD_REQUEST;
        logger.error(e.getMessage());
        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), request.getServletPath()));
    }
}
