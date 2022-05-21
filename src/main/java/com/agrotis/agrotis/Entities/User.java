package com.agrotis.agrotis.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private LocalDate initialDate;
  private LocalDate EndDate;

  private String comments;

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
    return EndDate;
  }

  /**
   * @param EndDate the EndDate to set
   */
  public void setEndDate(LocalDate EndDate) {
    this.EndDate = EndDate;
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

}