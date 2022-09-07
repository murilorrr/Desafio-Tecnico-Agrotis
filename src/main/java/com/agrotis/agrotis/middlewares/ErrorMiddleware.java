package com.agrotis.agrotis.middlewares;

import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroEntidadeNaoEncontrada;
import com.agrotis.agrotis.messages.ErrorMessageDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorMiddleware {

  @ExceptionHandler(ErroEntidadeNaoEncontrada.class)
  public ResponseEntity<ErrorMessageDefault> entityNotFound(ErroEntidadeNaoEncontrada e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(ErroDeChave.class)
  public ResponseEntity<ErrorMessageDefault> entityBadRequest(ErroDeChave e) {
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
