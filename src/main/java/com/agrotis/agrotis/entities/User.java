package com.agrotis.agrotis.entities;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Laboratorio getLaboratorio() {
    return laboratorio;
  }

  public void setLaboratorio(Laboratorio laboratorio) {
    this.laboratorio = laboratorio;
  }

  public Propriedade getPropriedade() {
    return propriedade;
  }

  public void setPropriedade(Propriedade propriedade) {
    this.propriedade = propriedade;
  }

}