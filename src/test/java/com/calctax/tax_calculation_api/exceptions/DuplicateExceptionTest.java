package com.calctax.tax_calculation_api.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


class DuplicateExceptionTest {
    @Test
    void testDuplicateException(){
        String errorMessage = "Informação não duplicada!";

        DuplicateException exception = assertThrows(DuplicateException.class, () -> {
            throw new DuplicateException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}