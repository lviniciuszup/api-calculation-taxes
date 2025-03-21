package com.calctax.tax_calculation_api.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RequestForCalculatedTaxDTOTest {
    @Test
    void testRequestForCalculatedTaxDTO() {

        RequestForCalculatedTaxDTO dto = new RequestForCalculatedTaxDTO(1L, BigDecimal.valueOf(15));

        assertEquals(1L, dto.getTaxId());
        assertEquals(15, dto.getBaseValue().intValue());

    }

}