package com.calctax.tax_calculation_api.exceptions;

public class DuplicateException extends RuntimeException {
  public DuplicateException(String message) {
    super(message);
  }
}
