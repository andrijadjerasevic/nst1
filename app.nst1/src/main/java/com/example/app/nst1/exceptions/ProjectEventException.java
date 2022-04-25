package com.example.app.nst1.exceptions;

public class ProjectEventException extends Exception {

  public ProjectEventException() {
    super();
  }

  public ProjectEventException(String message) {
    super(message);
  }

  public ProjectEventException(String message, Throwable cause) {
    super(message, cause);
  }
}
