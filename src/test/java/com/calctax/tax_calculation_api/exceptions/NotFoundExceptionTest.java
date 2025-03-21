package com.calctax.tax_calculation_api.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


class NotFoundExceptionTest {
    @Test
    void testNotFoundException(){
        String errorMessage = "Informação não encontrada!";

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

}