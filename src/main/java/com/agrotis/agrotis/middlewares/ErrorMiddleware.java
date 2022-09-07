package com.agrotis.agrotis.middlewares;

import com.agrotis.agrotis.exceptions.KeyError;
import com.agrotis.agrotis.exceptions.ErrorEntityNotFound;
import com.agrotis.agrotis.messages.ErrorMessageDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorMiddleware {

  @ExceptionHandler(ErrorEntityNotFound.class)
  public ResponseEntity<ErrorMessageDefault> entityNotFound(ErrorEntityNotFound e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(KeyError.class)
  public ResponseEntity<ErrorMessageDefault> entityBadRequest(KeyError e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorMessageDefault> bugEntity(HttpMessageNotReadableException e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessageDefault> bugEntity(Exception e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }
}
