package com.calctax.tax_calculation_api.dtos;

import com.calctax.tax_calculation_api.models.Role;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTaxDTOTest {
    @Test
    void testResponseTaxDTO() {

        ResponseTaxDTO dto = new ResponseTaxDTO(1L, "IR", "Imposto sobre renda", BigDecimal.valueOf(15));


        assertEquals(1L, dto.getTaxId());
        assertEquals("IR", dto.getName());
        assertEquals("Imposto sobre renda", dto.getDescription());
        assertEquals(15, dto.getAliquot().intValue());

    }

}