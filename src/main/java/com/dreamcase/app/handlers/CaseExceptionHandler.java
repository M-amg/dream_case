package com.dreamcase.app.handlers;

import com.dreamcase.app.dto.ErrorDto;
import com.dreamcase.app.exceptions.CaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class CaseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                .code(HttpStatus.BAD_REQUEST.value()).build();
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleCaseNotFoundException(CaseNotFoundException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value()).build();
        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
    }
}
