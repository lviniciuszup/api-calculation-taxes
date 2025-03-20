package com.calctax.tax_calculation_api.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class TaxTest {
        @Test
        void taxTest() {

            Tax tax = new Tax("IR", "Imposto sobre renda", BigDecimal.valueOf(15));


            assertEquals("IR", tax.getTaxName());
            assertEquals("Imposto sobre renda", tax.getDescription());
            assertEquals(BigDecimal.valueOf(15), tax.getAliquot());

        }


}