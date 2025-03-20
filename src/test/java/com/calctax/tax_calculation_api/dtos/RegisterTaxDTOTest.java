package com.calctax.tax_calculation_api.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTaxDTOTest {

        @Test
        void testRegisterTaxDTO() {

            RegisterTaxDTO dto = new RegisterTaxDTO("IR", "Imposto sobre renda", BigDecimal.valueOf(15));


            assertEquals("IR", dto.getName());
            assertEquals("Imposto sobre renda", dto.getDescription());
            assertEquals(15, dto.getAliquot().intValue());
        }
    }
