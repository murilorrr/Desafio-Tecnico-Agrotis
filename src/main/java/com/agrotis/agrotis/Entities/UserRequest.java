package com.agrotis.agrotis.Entities;

import java.time.LocalDate;

public class UserRequest {

  private String name;

  private LocalDate initialDate;

  private LocalDate endDate;

  private String comments;

  private String laboratorio;

  private String Propriedade;

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return LocalDate return the initialDate
   */
  public LocalDate getInitialDate() {
    return initialDate;
  }

  /**
   * @param initialDate the initialDate to set
   */
  public void setInitialDate(LocalDate initialDate) {
    this.initialDate = initialDate;
  }

  /**
   * @return LocalDate return the endDate
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * @return String return the comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments the comments to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * @return String return the laboratorio
   */
  public String getLaboratorio() {
    return laboratorio;
  }

  /**
   * @param laboratorio the laboratorio to set
   */
  public void setLaboratorio(String laboratorio) {
    this.laboratorio = laboratorio;
  }

  /**
   * @return String return the Propriedade
   */
  public String getPropriedade() {
    return Propriedade;
  }

  /**
   * @param Propriedade the Propriedade to set
   */
  public void setPropriedade(String Propriedade) {
    this.Propriedade = Propriedade;
  }

}
