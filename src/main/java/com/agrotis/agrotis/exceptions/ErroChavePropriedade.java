package com.agrotis.agrotis.exceptions;

public class ErroChavePropriedade extends ErroDeChave {
  public ErroChavePropriedade(String nome) {
    super("Não existe propriedade com esse nome ou esse cnpj: " + nome);
  }
}
