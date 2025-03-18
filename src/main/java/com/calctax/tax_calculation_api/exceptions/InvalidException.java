package com.calctax.tax_calculation_api.exceptions;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
