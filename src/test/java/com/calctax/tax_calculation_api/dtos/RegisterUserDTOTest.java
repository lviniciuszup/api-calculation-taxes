package com.calctax.tax_calculation_api.dtos;

import com.calctax.tax_calculation_api.models.Role;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserDTOTest {
    @Test
    void testRegisterUserDTO() {

        Set<String> roles = Set.of("ADMIN", "USER");

        RegisterUserDTO dto = new RegisterUserDTO("userTest", "123456", roles);


        assertEquals("userTest", dto.getUsername());
        assertEquals("123456", dto.getPassword());
        assertTrue(dto.getRoles().contains("ADMIN"));
        assertTrue(dto.getRoles().contains("USER"));

    }

}