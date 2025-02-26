package com.example.tutorialv2.exceptions.handler;

import com.example.tutorialv2.exceptions.ExceptionResponse;
import com.example.tutorialv2.exceptions.InvalidJwtAuthenticationException;
import com.example.tutorialv2.exceptions.MethodArgumentNotValidException;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllExceptions
            (Exception ex, WebRequest request ){

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
            ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NaoEncontradoException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequestExceptions (Exception ex, WebRequest request ){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handerMethodArgumentNotValidException
            (Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
                    return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handerInvalidJwtAuthenticationException
            (Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
                    return new ResponseEntity<>(exceptionResponse,HttpStatus.FORBIDDEN);

    }



}
