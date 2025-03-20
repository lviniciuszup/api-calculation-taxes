package com.calctax.tax_calculation_api.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatedTaxDTOTest {
    @Test
    void testCalculatedTaxDTO() {
        String name = "IR";
        BigDecimal baseValue = BigDecimal.valueOf(11);
        BigDecimal aliquot = BigDecimal.valueOf(13);

        BigDecimal calculatedValue = baseValue.multiply(aliquot).divide(BigDecimal.valueOf(100));

        CalculatedTaxDTO dto = new CalculatedTaxDTO(name, baseValue, aliquot, calculatedValue);

        assertEquals(name, dto.getName());
        assertEquals(baseValue, dto.getBaseValue());
        assertEquals(aliquot, dto.getAliquot());
        assertEquals(calculatedValue, dto.getCalculatedValue());
    }
}