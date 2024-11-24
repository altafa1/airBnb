package com.airbnb.exception;

import com.airbnb.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReviewExist.class)
    public ResponseEntity<ErrorDetails> handleReviewExist(
            ReviewExist e,
            WebRequest request
    ){
        ErrorDetails er=new ErrorDetails(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(
            ResourceNotFound e,
            WebRequest request
    ){
        ErrorDetails er=new ErrorDetails(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(
            Exception e, WebRequest request
            ){
        ErrorDetails er=new ErrorDetails(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }
}
