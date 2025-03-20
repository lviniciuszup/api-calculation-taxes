package com.calctax.tax_calculation_api.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


class InvalidExceptionTest {
    @Test
    void testInvalidExceptionTest(){
        String errorMessage = "Erro de validação!";

        InvalidException exception = assertThrows(InvalidException.class, () -> {
            throw new InvalidException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

}