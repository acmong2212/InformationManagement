package com.informationmanagement.demo.exception;

import com.informationmanagement.demo.dto.response.FailedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> nullPointer(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "999", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> nullPointer1(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "999", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> idNotFound(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "808", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
