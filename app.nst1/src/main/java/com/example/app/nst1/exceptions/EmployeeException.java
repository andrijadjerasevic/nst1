package com.example.app.nst1.exceptions;

public class EmployeeException extends Exception {
  public EmployeeException() {
    super();
  }

  public EmployeeException(String message) {
    super(message);
  }

  public EmployeeException(String message, Throwable cause) {
    super(message, cause);
  }
}
