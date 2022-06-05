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
  
  /**
   * Entidade responsavel por gerenciar os errosNotFound que são lançados na camada de services.
   * @param e Erro NotFound passado pelo service
   */
  @ExceptionHandler(ErroEntidadeNaoEncontrada.class)
  public ResponseEntity<ErrorMessageDefault> entityNotFound(ErroEntidadeNaoEncontrada e) {
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

  /**
   * Entidade responsavel por gerenciar os Erros de Requests body.
   * @param e Exception passado pelo service
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorMessageDefault> bugEntity(HttpMessageNotReadableException e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  /**
   * Entidade responsavel por gerenciar os Erros totais que são lançados por algum bug
   * ainda desconhecido.
   * @param e Exception passado pelo service
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessageDefault> bugEntity(Exception e) {
    ErrorMessageDefault err = new ErrorMessageDefault(e.getMessage());
    err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }
}
