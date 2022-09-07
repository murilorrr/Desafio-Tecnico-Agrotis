package com.agrotis.agrotis.exceptions;

public class KeyErrorLaboratory extends KeyError {

  public KeyErrorLaboratory(String nome) {
    super("Não existe ou não pode existir laboratorio com esse nome: " + nome);
  }
  
}
