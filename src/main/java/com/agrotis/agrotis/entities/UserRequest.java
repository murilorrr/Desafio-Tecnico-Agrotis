package com.agrotis.agrotis.entities;

import java.time.LocalDate;

public class UserRequest {

  private String name;

  private LocalDate initialDate;

  private LocalDate endDate;

  private String comments;

  private String laboratory;

  private String propriedade;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getInitialDate() {
    return initialDate;
  }

  public void setInitialDate(LocalDate initialDate) {
    this.initialDate = initialDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getLaboratory() {
    return laboratory;
  }

  public void setLaboratory(String laboratory) {
    this.laboratory = laboratory;
  }

  public String getPropriedade() {
    return propriedade;
  }

  public void setPropriedade(String propriedade) {
    this.propriedade = propriedade;
  }

}
