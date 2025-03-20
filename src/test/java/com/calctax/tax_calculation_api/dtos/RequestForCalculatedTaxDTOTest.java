package com.calctax.tax_calculation_api.dtos;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RequestForCalculatedTaxDTOTest {
    void testRequestForCalculatedTaxDTO() {

        RequestForCalculatedTaxDTO dto = new RequestForCalculatedTaxDTO(1L, BigDecimal.valueOf(15));


        assertEquals(1L, dto.getTaxId());
        assertEquals(15, dto.getBaseValue());

    }

}