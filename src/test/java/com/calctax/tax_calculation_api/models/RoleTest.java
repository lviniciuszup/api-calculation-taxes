package com.calctax.tax_calculation_api.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


    class RoleTest {
        @Test
        void roleTest() {

            Role role = new Role(1L, "ADMIN");;
            assertEquals(1L, role.getId());
            assertEquals("ADMIN", role.getName());
        }

}