package com.agrotis.agrotis.controllers;

import com.agrotis.agrotis.exceptions.ErroDeChave;
import com.agrotis.agrotis.exceptions.ErroNaoEncontrado;
import com.agrotis.agrotis.messages.ErrorMessageDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
  
  /**
   * Entidade responsavel por gerenciar os errosNotFound que são lançados na camada de services.
   * @param e Erro NotFound passado pelo service
   */
  @ExceptionHandler(ErroNaoEncontrado.class)
  public ResponseEntity<ErrorMessageDefault> entityNotFound(ErroNaoEncontrado e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  /**
   * Entidade responsavel por gerenciar os erros de chave que são lançados na camada de
   * services(e as vezes controlers).
   * @param e ErroDeChave passado pelo service
   */
  @ExceptionHandler(ErroDeChave.class)
  public ResponseEntity<ErrorMessageDefault> entityBadRequest(ErroDeChave e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
}
