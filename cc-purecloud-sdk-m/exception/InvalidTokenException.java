package com.sdk.exception;

public class InvalidTokenException extends Exception {
  
  public InvalidTokenException() {
    super();
  }
  
  public InvalidTokenException(String message) {
    super(message);
  }
}
