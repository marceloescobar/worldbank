package com.mescobar.worldbankapi.exception;

public class ConsumeException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;



  public ConsumeException(String message) {
    super(message);
  }

  public ConsumeException(String message, Throwable cause) {
    super(message, cause);
  }
}
