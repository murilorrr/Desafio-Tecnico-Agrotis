package com.agrotis.agrotis.exceptions;

public class KeyErrorProperty extends KeyError {
  public KeyErrorProperty(String nome) {
    super("Não existe propriedade com esse nome ou esse cnpj: " + nome);
  }
}
