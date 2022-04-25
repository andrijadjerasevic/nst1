package com.example.app.nst1.exceptions;

public class AdminException extends Exception {

  public AdminException() {
    super();
  }

  public AdminException(String message) {
    super(message);
  }

  public AdminException(String message, Throwable cause) {
    super(message, cause);
  }
}
