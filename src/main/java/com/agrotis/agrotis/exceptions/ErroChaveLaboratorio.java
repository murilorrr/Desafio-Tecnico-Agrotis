package com.agrotis.agrotis.exceptions;

public class ErroChaveLaboratorio extends ErroDeChave {

  public ErroChaveLaboratorio(String nome) {
    super("Não existe laboratorio com esse nome: " + nome);
  }
  
}
