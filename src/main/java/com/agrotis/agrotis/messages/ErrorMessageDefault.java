package com.agrotis.agrotis.messages;

import java.time.Instant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessageDefault {
  private Instant instant;
  private int status;
  private String message;

  public ErrorMessageDefault( String message) {
    this.message = message;
    instant = Instant.now();
  }

  public Instant getInstant() {
    return instant;
  }

  public void setInstant(Instant instant) {
    this.instant = instant;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
