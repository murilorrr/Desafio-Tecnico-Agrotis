package com.agrotis.agrotis.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Name is mandatory")
  private String name;

  // @NotEmpty(message = "initialDate is mandatory")
  private LocalDate initialDate;

  // @NotBlank(message = "endDate is mandatory")
  private LocalDate endDate;

  private String comments;

  @ManyToOne
  @JoinColumn(name = "laboratorio_id")
  private Laboratorio laboratorio;

  @ManyToOne
  @JoinColumn(name = "propriedade_id")
  private Propriedade propriedade;

  // getters and setters
  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

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
   * @return LocalDateTime return the initialDate
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
   * @return LocalDateTime return the EndDate
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * @param EndDate the EndDate to set
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
     * @return Laboratorio return the laboratorio
     */
    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    /**
     * @param laboratorio the laboratorio to set
     */
    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * @return Propriedade return the propriedade
     */
    public Propriedade getPropriedade() {
        return propriedade;
    }

    /**
     * @param propriedade the propriedade to set
     */
    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }
    // getters and setters

}